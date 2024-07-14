package com.dy.model.dto.question;

import lombok.Data;

/**
 * @Author: dy
 * @Date: 2024/7/14 10:56
 * @Description:
 */
@Data
public class JudgeConfig {


    /**
     * 时间限制
     */
    String timeLimit;
    /**
     * 内存限制
     */
    String memoryLimit;
    /**
     * 堆栈限制
     */
    String stackLimit;
}
