package com.ggb.complete_set.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data  // Lombok注解，自动生成getter/setter等方法
    @NoArgsConstructor   // 生成无参构造函数
    public class Result<T> {
        private Integer code;    // 响应状态码
        private String message;  // 响应消息
        private T data;         // 响应数据

        public Result(Integer code, String message, T data) {
            this.code = code;
            this.data = data;
            this.message = message;
        }

        // 成功响应静态方法
        public static <T> Result<T> success(T data) {
            return new Result<>(200, "success", data);
        }

        // 错误响应静态方法
        public static <T> Result<T> error(String message) {
            return new Result<>(500, message, null);
        }
    }

