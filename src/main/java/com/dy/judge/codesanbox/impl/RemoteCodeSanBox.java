package com.dy.judge.codesanbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dy.judge.codesanbox.CodeSanBox;
import com.dy.judge.codesanbox.model.ExecuteCodeRequest;
import com.dy.judge.codesanbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:19
 * @Description: 远程代码沙箱(我们自己实现)
 */
@Slf4j
@Service
public class RemoteCodeSanBox implements CodeSanBox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("调用远程代码沙箱");
        String url = "http://localhost:8081/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);

        //  利用 hutool 工具类调用
        String responseStr = HttpUtil.createPost(url)
                .body(jsonStr)
                .execute()
                .body();
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);

        log.info("请求得到的结果: {}", executeCodeResponse);
        return executeCodeResponse;
    }
}
