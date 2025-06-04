package com.ggb.complete_set.service;

import com.ggb.complete_set.model.dto.*;

public interface UserService {
    // 用户注册 - 处理新用户注册的业务逻辑
    UserDTO register(RegisterRequest request);

    // 用户登录 - 处理用户登录认证的业务逻辑
    LoginResponse login(LoginRequest request);

    // 更新用户信息 - 处理用户信息更新的业务逻辑
    UserDTO updateUser(Long id, UpdateUserRequest request);

    // 修改密码 - 处理用户密码修改的业务逻辑
    void changePassword(Long id, ChangePasswordRequest request);

    // 重置密码 - 处理用户密码重置的业务逻辑
    void resetPassword(String email);

    // 删除用户 - 处理用户删除的业务逻辑
    void deleteUser(Long id);
}
