package com.dy.judge.codesanbox.impl;

import com.dy.judge.codesanbox.CodeSanBox;
import com.dy.judge.codesanbox.model.ExecuteCodeRequest;
import com.dy.judge.codesanbox.model.ExecuteCodeResponse;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:19
 * @Description: 远程代码沙箱(我们自己实现)
 */
public class RemoteCodeSanBox implements CodeSanBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("Remote 沙箱执行");
        return null;
    }
}
