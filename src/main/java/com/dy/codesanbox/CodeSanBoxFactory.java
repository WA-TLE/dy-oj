package com.dy.codesanbox;

import com.dy.codesanbox.impl.ExampleCodeSanBox;
import com.dy.codesanbox.impl.RemoteCodeSanBox;
import com.dy.codesanbox.impl.ThirdPartyCodeSanBox;


/**
 * @Author: dy
 * @Date: 2024/7/16 17:33
 * @Description: 代码沙箱生产工厂
 */
public class CodeSanBoxFactory {
    CodeSanBox createCodeSanBox(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSanBox();
            case "remote":
                return new RemoteCodeSanBox();
            case "thridParty":
                return new ThirdPartyCodeSanBox();
            default:
                return new ExampleCodeSanBox();
        }
    }
}
