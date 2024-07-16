package com.dy.codesanbox;

import com.dy.codesanbox.impl.ExampleCodeSanBox;
import com.dy.codesanbox.model.ExecuteCodeRequest;
import com.dy.codesanbox.model.ExecuteCodeResponse;
import com.dy.codesanbox.proxy.CodeSanBoxProxy;
import com.dy.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: dy
 * @Date: 2024/7/16 17:22
 * @Description:
 */
@SpringBootTest
class CodeSanBoxTest {

    @Value("${codeSanBox.type}")
    private String type;
    @Test
    void textCodeSanBox() {
//        ExampleCodeSanBox exampleCodeSanBox = new ExampleCodeSanBox();

        CodeSanBoxFactory codeSanBoxFactory = new CodeSanBoxFactory();
        System.out.println(type);
        CodeSanBox codeSanBox = codeSanBoxFactory.createCodeSanBox(type);
        CodeSanBoxProxy codeSanBoxProxy = new CodeSanBoxProxy(codeSanBox);

        String code = "int main()";
        List<String> inputList = Arrays.asList("1, 2, 3", "3, 4, 5");
        String value = QuestionSubmitLanguageEnum.JAVA.getValue();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .inputList(inputList)
                .language(value)
                .code(code)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSanBoxProxy.executeCode(executeCodeRequest);

        System.out.println(executeCodeResponse);
    }
}