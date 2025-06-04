package com.ggb.complete_set.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum UserStatus {
//    ACTIVE（活跃）
//    INACTIVE（未激活）
//    LOCKED（已锁定）
//    DELETED（已删除）

    ACTIVE(1, ""),
    INACTIVE(2,""),
    LOCKED(3,""),
    DELETED(4, "");

    @Setter
    private Integer code;
    @Setter
    private String message;

}
