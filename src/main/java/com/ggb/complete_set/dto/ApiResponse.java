package com.ggb.complete_set.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//它自动为类生成所有字段的 getter 和 setter 方法，
// 同时还会生成 toString()、equals() 和 hashCode() 方法，以及一个无参构造函数
@Data
//这个注解会自动生成一个无参构造函数，当类中没有定义任何构造函数时特别有用，
// 它确保即使没有显式定义构造函数，类也能被正常实例化。
@NoArgsConstructor
//这个注解会自动生成一个包含所有字段的构造函数，参数顺序与类中字段的声明顺序一致，方便在创建对象时一次性初始化所有属性。
//@AllArgsConstructor
@SuppressWarnings("all")
public class ApiResponse<T> implements Serializable {
    //   Serializable   确保数据的一致性和兼容性。

    public ApiResponse(Integer code, String message,T data , LocalDateTime timestamp) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.timestamp = timestamp;
    }

    private static final long serialVersionUID = 1L;

    private Integer code; // 业务状态码，例如：200成功，400参数错误，500服务器内部错误
    private String message; // 消息提示
    private T data; // 返回的数据
    private LocalDateTime timestamp; // 响应时间戳

    // 静态工厂方法用于创建成功响应
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "操作成功", data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "操作成功", null, LocalDateTime.now());
    }

    // 静态工厂方法用于创建失败响应
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message, null, LocalDateTime.now());
    }
}
