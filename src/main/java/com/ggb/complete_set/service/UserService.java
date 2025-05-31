package com.ggb.complete_set.service;

import com.ggb.complete_set.model.dto.UserDTO;
import com.ggb.complete_set.model.entity.User;
import com.ggb.complete_set.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional//事务支持
//自动开启事务，自动提交事务，发生异常时自动回滚事务，支持事务传播行为，支持事务隔离级别
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 转换方法
    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return user;
    }

    //创建单个用户
    public User createUser(UserDTO userDTO){
        return userRepository.save(convertToUser(userDTO));
    }

    //批量创建用户
    public List<User> createUsers(List<UserDTO> userDTOS){
        List<User> users = userDTOS.stream()
                .map(this::convertToUser)
                .toList();
        return userRepository.saveAll(users);
    }
}
