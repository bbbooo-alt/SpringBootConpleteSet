package com.ggb.complete_set.common.exception;

import com.ggb.complete_set.common.response.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理器
@RestControllerAdvice  // 标识这是一个全局异常处理类
public class GlobalExceptionHandler {
    // 处理所有异常
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
