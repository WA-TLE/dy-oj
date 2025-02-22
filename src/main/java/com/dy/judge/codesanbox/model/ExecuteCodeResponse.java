package com.dy.judge.codesanbox.model;

import com.dy.model.dto.questionsubmit.JudgeInfo;
import com.dy.model.enums.QuestionSubmitStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:13
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeResponse {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 输出用例列表
     */
    private List<String> outputList;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 程序执行状态
     */
    private String  status;

    
}
