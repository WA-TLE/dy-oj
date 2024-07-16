package com.dy.codesanbox;

import com.dy.codesanbox.model.ExecuteCodeRequest;
import com.dy.codesanbox.model.ExecuteCodeResponse;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:08
 * @Description:
 */
public interface CodeSanBox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
