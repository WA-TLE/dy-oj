package com.dy.judge.strategy;

import com.dy.judge.JudgeContext;
import com.dy.model.dto.questionsubmit.JudgeInfo;

/**
 * @Author: dy
 * @Date: 2024/7/17 23:42
 * @Description:
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
