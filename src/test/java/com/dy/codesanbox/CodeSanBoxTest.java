package com.dy.codesanbox;

import com.dy.codesanbox.impl.ExampleCodeSanBox;
import com.dy.codesanbox.model.ExecuteCodeRequest;
import com.dy.codesanbox.model.ExecuteCodeResponse;
import com.dy.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
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
    @Test
    void textCodeSanBox() {
        ExampleCodeSanBox exampleCodeSanBox = new ExampleCodeSanBox();
        String code = "int main()";
        List<String> inputList = Arrays.asList("1, 2, 3", "3, 4, 5");
        String value = QuestionSubmitLanguageEnum.JAVA.getValue();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .inputList(inputList)
                .language(value)
                .code(code)
                .build();
        ExecuteCodeResponse executeCodeResponse = exampleCodeSanBox.executeCode(executeCodeRequest);

    }
}