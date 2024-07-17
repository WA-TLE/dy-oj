package com.dy.judge;

import com.dy.model.entity.Question;

/**
 * @Author: dy
 * @Date: 2024/7/17 21:38
 * @Description:
 */
public interface JudgeService {
    Question doJudge(Long questionSubmitId);
}
