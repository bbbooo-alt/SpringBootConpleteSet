package com.ggb.complete_set.service;

import com.ggb.complete_set.model.dto.UserDTO;
import com.ggb.complete_set.model.entity.User;
import com.ggb.complete_set.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
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

    //读取用户
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("用户不存在"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Page<User> getAllUserByPage(){
        return userRepository.findAll(PageRequest.of(0, 10));
    }

    //更新
    public User update(Long id, UserDTO userDTO){
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("用户不存在"));

        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());

        return userRepository.save(user);
    }

    //解释：
    //上一个方法的返回类型是User
    //这个方法是void
    //原因是上一条方法有多条修改语句，返回user可以方便展示修改效果
    //而本方法只修改一条语句，所以可以通过一个变量就展示出来是否成功修改
    public void updateStatus(Long id, Integer status){
        int update = userRepository.updateUserStatus(id, status);
        if(update == 0){
            throw new EntityNotFoundException("用户不存在");
        }
    }

    //删除
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    //批量删除
    public void deleteUsers(List<Long> ids){
        userRepository.deleteAllById(ids);
    }
}
