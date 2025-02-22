package com.dy.judge;

import com.dy.judge.strategy.DefaultJudgeStrategy;
import com.dy.judge.strategy.JavaLanguageJudgeStrategy;
import com.dy.judge.strategy.JudgeStrategy;
import com.dy.model.dto.questionsubmit.JudgeInfo;
import com.dy.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        // TODO: 2024/7/23 这里关于判题机制的选择, 可以再优雅一点
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
