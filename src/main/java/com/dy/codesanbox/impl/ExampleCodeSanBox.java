package com.dy.codesanbox.impl;
import com.dy.model.dto.questionsubmit.JudgeInfo;
import java.util.ArrayList;
import java.util.List;

import com.dy.codesanbox.CodeSanBox;
import com.dy.codesanbox.model.ExecuteCodeRequest;
import com.dy.codesanbox.model.ExecuteCodeResponse;
import com.dy.model.enums.JudgeInfoMessageEnum;
import com.dy.model.enums.QuestionSubmitStatusEnum;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:19
 * @Description: 用于测试程序
 */
public class ExampleCodeSanBox implements CodeSanBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String language = executeCodeRequest.getLanguage();
        String code = executeCodeRequest.getCode();
        List<String> inputList = executeCodeRequest.getInputList();
        System.out.println("example 沙箱执行");

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage("");
        executeCodeResponse.setOutputList(new ArrayList<>());
        executeCodeResponse.setJudgeInfo(new JudgeInfo());






        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getText());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;




    }
}
