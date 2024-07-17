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
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
