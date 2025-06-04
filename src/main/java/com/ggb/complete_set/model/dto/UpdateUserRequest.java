package com.ggb.complete_set.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @Email(message = "邮箱格式不正确")
    private String email;       // 邮箱

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;       // 手机号

    @Size(max = 100, message = "昵称长度不能超过100个字符")
    private String nickname;    // 昵称

    @Size(max = 200, message = "个人简介长度不能超过200个字符")
    private String bio;         // 个人简介

    private String avatar;      // 头像URL

    private String gender;      // 性别

    private String birthday;    // 生日

    private String address;     // 地址
}
