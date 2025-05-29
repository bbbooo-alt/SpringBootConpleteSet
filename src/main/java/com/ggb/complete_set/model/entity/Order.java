package com.ggb.complete_set.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// 订单实体类：对应t_order表
@Entity  // 标识这是一个JPA实体类
@Table(name = "t_order")  // 指定对应的数据库表名
@Data  // Lombok注解，自动生成getter/setter等方法
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id  // 标识这是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略：自增
    private Long id;  // 订单ID

    @Column(nullable = false)  // 数据库列定义：非空
    private Long userId;  // 用户ID

    @NotBlank(message = "订单号不能为空")  // 非空验证
    @Size(min = 10, max = 50, message = "订单号长度必须在10-50之间")  // 长度验证
    @Column(nullable = false, unique = true)  // 数据库列定义：非空，唯一
    private String orderNo;  // 订单号

    @NotNull(message = "订单金额不能为空")  // 非空验证
    @DecimalMin(value = "0.01", message = "订单金额必须大于0")  // 最小值验证
    @DecimalMax(value = "999999.99", message = "订单金额不能超过999999.99")  // 最大值验证
    @Column(nullable = false, precision = 10, scale = 2)  // 数据库列定义：非空，精度设置
    private BigDecimal amount;  // BigDecimal专用于处理金额的数据类型

    @Column(nullable = false)  // 数据库列定义：非空
    private Integer status = 0;  // 订单状态：0-待处理，1-已处理，2-已完成，3-已取消

    private LocalDateTime createdAt;  // 创建时间
    private LocalDateTime updatedAt;  // 更新时间
}