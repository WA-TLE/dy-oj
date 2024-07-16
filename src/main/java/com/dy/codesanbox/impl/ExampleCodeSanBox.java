package com.dy.codesanbox.impl;

import com.dy.codesanbox.CodeSanBox;
import com.dy.codesanbox.model.ExecuteCodeRequest;
import com.dy.codesanbox.model.ExecuteCodeResponse;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:19
 * @Description: 用于测试程序
 */
public class ExampleCodeSanBox implements CodeSanBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("example 沙箱执行");
        return null;
    }
}
