package com.dy.service;

import com.dy.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dy.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.model.entity.User;

/**
* @author 微光
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-07-14 14:18:23
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


}
