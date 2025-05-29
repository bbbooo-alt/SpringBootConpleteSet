package com.ggb.complete_set.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// 权限实体类：对应t_permission表
@Entity  // 标识这是一个JPA实体类
@Table(name = "t_permission")  // 指定对应的数据库表名
@Data  // Lombok注解，自动生成getter/setter等方法
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id  // 标识这是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略：自增
    private Long id;  // 权限ID

    @NotBlank(message = "权限名称不能为空")  // 非空验证
    @Size(min = 2, max = 50, message = "权限名称长度必须在2-50之间")  // 长度验证
    @Column(nullable = false, unique = true)  // 数据库列定义：非空，唯一
    private String name;  // 权限名称

    @NotBlank(message = "权限编码不能为空")  // 非空验证
    @Size(min = 2, max = 50, message = "权限编码长度必须在2-50之间")  // 长度验证
    @Column(nullable = false, unique = true)  // 数据库列定义：非空，唯一
    private String code;  // 权限编码

    private String description;  // 权限描述
    private LocalDateTime createdAt;  // 创建时间
}