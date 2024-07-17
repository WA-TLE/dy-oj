package com.dy.judge;

import com.dy.model.dto.question.JudgeConfig;
import com.dy.model.dto.questionsubmit.JudgeInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.dy.common.ErrorCode;
import com.dy.exception.BusinessException;
import com.dy.judge.codesanbox.CodeSanBox;
import com.dy.judge.codesanbox.CodeSanBoxFactory;
import com.dy.judge.codesanbox.model.ExecuteCodeRequest;
import com.dy.judge.codesanbox.model.ExecuteCodeResponse;
import com.dy.judge.codesanbox.proxy.CodeSanBoxProxy;
import com.dy.model.dto.question.JudgeCase;
import com.dy.model.entity.Question;
import com.dy.model.entity.QuestionSubmit;
import com.dy.model.enums.JudgeInfoMessageEnum;
import com.dy.model.enums.QuestionSubmitStatusEnum;
import com.dy.service.QuestionService;
import com.dy.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: dy
 * @Date: 2024/7/17 21:39
 * @Description:
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private QuestionService questionService;

    @Resource
    private CodeSanBoxFactory codeSanBoxFactory;

    @Value("${codeSanBox.type}")
    private String type;

    @Override
    public Question doJudge(Long questionSubmitId) {
        if (questionSubmitId == null || questionSubmitId < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //  获取用户提交信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        Long questionId = questionSubmit.getQuestionId();
        Integer questionSubmitStatus = questionSubmit.getStatus();

        if (!Objects.equals(QuestionSubmitStatusEnum.WAITING.getValue(), questionSubmitStatus)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "程序已经执行判题");
        }

        // TODO: 2024/7/17 限制用户重复判题

        //  更新题目信息
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        questionSubmitUpdate.setQuestionId(questionId);
        questionSubmitUpdate.setId(questionSubmitId);
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目信息失败");
        }

        //  获取题目信息
        Question question = questionService.getById(questionId);

        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCases = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> standardInputList = judgeCases.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        List<String> standardOutputList = judgeCases.stream().map(JudgeCase::getOutput).collect(Collectors.toList());

        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);

        //  调用沙箱, 获取代码执行信息
        CodeSanBox codeSanBox = codeSanBoxFactory.createCodeSanBox(type);
        CodeSanBoxProxy codeSanBoxProxy = new CodeSanBoxProxy(codeSanBox);

        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setLanguage(language);
        executeCodeRequest.setCode(code);
        executeCodeRequest.setInputList(standardInputList);
        ExecuteCodeResponse executeCodeResponse = codeSanBoxProxy.executeCode(executeCodeRequest);

        String message = executeCodeResponse.getMessage();
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
        long time = judgeInfo.getTime();
        long memory = judgeInfo.getMemory();
        String status = executeCodeResponse.getStatus();

        //  跟新沙箱的执行结果, 判断用户的提交状态
        //  输出大小是否一样
        if (outputList.size() != standardOutputList.size()) {
            judgeInfo.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
        }

        for (int i = 0; i < standardOutputList.size(); i++) {
            if (!standardOutputList.get(i).equals(outputList.get(i))) {
                judgeInfo.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
                break;
            }
        }
        // TODO: 2024/7/17 提交题目时 judgeConfig 没有一块提交
        if (time > judgeConfig.getTimeLimit()) {
            judgeInfo.setMessage(JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue());
        }
        if (memory > judgeConfig.getMemoryLimit()) {
            judgeInfo.setMessage(JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getValue());
        }

        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());


        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }




        return questionService.getById(questionId);
    }
}
