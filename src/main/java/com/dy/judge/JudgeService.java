package com.dy.judge;

import com.dy.model.entity.Question;
import com.dy.model.entity.QuestionSubmit;

/**
 * @Author: dy
 * @Date: 2024/7/17 21:38
 * @Description:
 */
public interface JudgeService {
    QuestionSubmit doJudge(Long questionSubmitId);
}
