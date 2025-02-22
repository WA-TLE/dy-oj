package com.dy.judge.strategy;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.dy.model.dto.question.JudgeCase;
import com.dy.model.dto.question.JudgeConfig;
import com.dy.model.entity.Question;
import com.dy.model.entity.QuestionSubmit;

import com.dy.judge.JudgeContext;
import com.dy.model.dto.questionsubmit.JudgeInfo;
import com.dy.model.enums.JudgeInfoMessageEnum;

/**
 * @Author: dy
 * @Date: 2024/7/17 23:43
 * @Description:
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        long time = judgeInfo.getTime();
        long memory = judgeInfo.getMemory();

        Question question = judgeContext.getQuestion();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);

        //  判断时间内存是否超出限制
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        // TODO: 2024/7/17 新增题目时 judgeConfig 没有一块提交
        if (time > judgeConfig.getTimeLimit()) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }
        if (memory > judgeConfig.getMemoryLimit()) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }


        //  判断测试用例
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        List<String> standardOutputList = judgeCaseList.stream().map(JudgeCase::getOutput).collect(Collectors.toList());
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();

        //  跟新沙箱的执行结果, 判断用户的提交状态
        //  输出大小是否一样
        if (outputList.size() != standardOutputList.size()) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
            return judgeInfoResponse;
        }

        for (int i = 0; i < standardOutputList.size(); i++) {
            if (!standardOutputList.get(i).equals(outputList.get(i))) {
                judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
               return judgeInfoResponse;
            }
        }


        judgeInfoResponse.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());

        return judgeInfoResponse;
    }
}
