package com.dy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dy.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dy.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.dy.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.model.entity.User;
import com.dy.model.vo.QuestionSubmitVO;

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

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
