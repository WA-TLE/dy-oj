package com.dy.judge.codesanbox.impl;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dy.judge.codesanbox.CodeSanBox;
import com.dy.judge.codesanbox.model.ExecuteCodeRequest;
import com.dy.judge.codesanbox.model.ExecuteCodeResponse;
import com.dy.model.dto.questionsubmit.JudgeInfo;
import java.util.ArrayList;
import java.util.List;

import com.dy.model.enums.JudgeInfoMessageEnum;
import com.dy.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:19
 * @Description: 用于测试程序
 */
@Slf4j
public class ExampleCodeSanBox implements CodeSanBox {


    //  定义鉴权请求头
    public static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET = "dingyu";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        String language = executeCodeRequest.getLanguage();
        String code = executeCodeRequest.getCode();
        List<String> inputList = executeCodeRequest.getInputList();
        System.out.println("example 沙箱执行");

        log.info("调用本地代码沙箱");
        String url = "http://localhost:8081/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);

        //  利用 hutool 工具类调用
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(jsonStr)
                .execute()
                .body();
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);

        log.info("请求得到的结果: {}", executeCodeResponse);
        return executeCodeResponse;
    }


}
