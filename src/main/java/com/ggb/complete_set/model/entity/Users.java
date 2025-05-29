package com.ggb.complete_set.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity// 标识这是一个JPA实体类
@Table(name = "users")// 指定对应的数据库表名
@Data
@EqualsAndHashCode//自动生成equals（）方法和hashcode（）方法
@SuppressWarnings("all")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;  // 序列化版本号

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 主键生成策略：自增
    private Long id;

    @NotBlank(message = "用户名不能为空")//非空
    @Size(min = 3, max = 50, message = "用户名长度需要在3~50之间")
    @Column(nullable = false, unique = true)//如果数据不满足条件，数据库会拒绝执行
    private String userName;

    @NotBlank(message = "密码不能为空")  // 非空验证
    @Size(min = 6, max = 100, message = "密码长度必须在6-100之间")  // 长度验证
    @Column(nullable = false)  // 数据库列定义：非空
    private String password;  // 密码

    @Email(message = "邮箱格式不正确")  // 邮箱格式验证
    @NotBlank(message = "邮箱不能为空")  // 非空验证
    @Column(nullable = false, unique = true)  // 数据库列定义：非空，唯一
    private String email;  // 邮箱

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")  // 检查字段值是否匹配指定的正则表达式模式
    private String phone;  // 手机号

    @Column(nullable = false)  // 数据库列定义：非空
    private Integer status = 1;  // 用户状态：1-正常，0-禁用

    private LocalDateTime createdAt;  // 创建时间
    private LocalDateTime updatedAt;  // 更新时间
}
