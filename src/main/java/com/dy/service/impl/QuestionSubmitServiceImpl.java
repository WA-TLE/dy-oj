package com.dy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.common.ErrorCode;
import com.dy.exception.BusinessException;
import com.dy.exception.ThrowUtils;
import com.dy.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dy.model.entity.Question;
import com.dy.model.entity.QuestionSubmit;
import com.dy.model.entity.QuestionSubmit;
import com.dy.model.entity.User;
import com.dy.model.enums.QuestionSubmitLanguageEnum;
import com.dy.model.enums.QuestionSubmitStatusEnum;
import com.dy.service.QuestionService;
import com.dy.service.QuestionSubmitService;
import com.dy.service.QuestionSubmitService;
import com.dy.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
* @author 微光
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-07-14 14:18:23
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        Long questionId = questionSubmitAddRequest.getQuestionId();

        //  判断语言我们是否支持
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum enumByValue = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (language == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }

        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }


        long userId = loginUser.getId();

        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        //  设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");

        // TODO: 2024/7/14 限制用户频繁提交
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目提交保存错误");
        }
        return questionSubmit.getId();
    }



}




