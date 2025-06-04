package com.ggb.complete_set.model.dto;

import com.ggb.complete_set.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long userId;        // 用户ID
    private String username;    // 用户名
    private String token;       // JWT令牌,和session功能差不多，更适合微服务，分布式系统
    private String refreshToken;// 刷新令牌
    private Date expireTime;    // 过期时间
    private UserStatus status;  // 用户状态
}
