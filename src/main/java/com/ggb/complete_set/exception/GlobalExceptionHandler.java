package com.ggb.complete_set.exception;

import com.ggb.complete_set.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice 是一个组合注解，
// 它结合了 @ControllerAdvice 和 @ResponseBody 的功能，
// 用于创建全局异常处理器，可以统一处理控制器层抛出的异常，并将处理结果自动转换为 JSON 格式返回给客户端，
// 这样就不需要在每个控制器中重复编写异常处理代码，提高了代码的复用性和可维护性。
@RestControllerAdvice
//该类的核心功能就是捕获异常，然后转化成ApiResponse类的形式返回
public class GlobalExceptionHandler {
    //@ExceptionHandler():
    //用于指定要处理的异常类型
    //可以处理多个异常，例如：@ExceptionHandler({Exception1.class, Exception2.class})


    //@ResponseStatus():
    //用于设置 HTTP 响应状态码
    //可以设置状态码和原因，例如：@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "资源未找到")
    //常用的状态码包括：
    //HttpStatus.OK (200)
    //HttpStatus.BAD_REQUEST (400)
    //HttpStatus.UNAUTHORIZED (401)
    //HttpStatus.FORBIDDEN (403)
    //HttpStatus.NOT_FOUND (404)
    //HttpStatus.INTERNAL_SERVER_ERROR (500)

    //这是一个处理服务器端异常的方法，
    // 还有处理参数校验异常 (例如 @Valid 注解引发的异常)的方法，具体比较复杂，后续细化的时候再学
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleAllException(Exception e){
        // 生产环境不建议直接返回异常详细信息，可记录日志并返回通用错误信息
        return ApiResponse.error(500, "服务器出错"+e.getMessage());
    }
}
