package com.ggb.complete_set.service;

import com.ggb.complete_set.model.dto.UserDTO;
import com.ggb.complete_set.model.entity.User;
import com.ggb.complete_set.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService 测试类")
class UserUserServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(UserUserServiceImplTest.class);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        log.info("开始设置测试数据...");
        // 初始化测试数据
        userDTO = new UserDTO();
        userDTO.setName("测试用户");
        userDTO.setEmail("test@example.com");
        userDTO.setPhone("1234567890");
        userDTO.setPassword("password123");

        user = new User();
        user.setId(1L);
        user.setUserName("测试用户");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setPassword("password123");
        log.info("测试数据设置完成");
    }

    @Nested
    @DisplayName("DTO转换测试")
    class ConvertToUserTests {
        @Test
        @DisplayName("DTO转换为User实体 - 所有字段正确转换")
        void convertToUser_ShouldConvertAllFieldsCorrectly() {
            log.info("开始测试 DTO 转换为 User 实体 - 所有字段正确转换");
            // 执行转换
            User result = userServiceImpl.convertToUser(userDTO);

            // 验证所有字段
            assertAll(
                () -> assertNotNull(result, "转换后的User对象不应为空"),
                () -> assertEquals(userDTO.getName(), result.getUserName(), "用户名应该正确转换"),
                () -> assertEquals(userDTO.getEmail(), result.getEmail(), "邮箱应该正确转换"),
                () -> assertEquals(userDTO.getPhone(), result.getPhone(), "电话应该正确转换"),
                () -> assertEquals(userDTO.getPassword(), result.getPassword(), "密码应该正确转换")
            );
            log.info("DTO 转换为 User 实体测试通过");
        }

        @Test
        @DisplayName("DTO转换为User实体 - 空值处理")
        void convertToUser_ShouldHandleNullValues() {
            log.info("开始测试 DTO 转换为 User 实体 - 空值处理");
            // 准备包含空值的DTO
            UserDTO nullDTO = new UserDTO();
            nullDTO.setName(null);
            nullDTO.setEmail(null);
            nullDTO.setPhone(null);
            nullDTO.setPassword(null);

            // 执行转换
            User result = userServiceImpl.convertToUser(nullDTO);

            // 验证结果
            assertAll(
                () -> assertNotNull(result, "转换后的User对象不应为空"),
                () -> assertNull(result.getUserName(), "用户名为null时应该保持为null"),
                () -> assertNull(result.getEmail(), "邮箱为null时应该保持为null"),
                () -> assertNull(result.getPhone(), "电话为null时应该保持为null"),
                () -> assertNull(result.getPassword(), "密码为null时应该保持为null")
            );
            log.info("空值处理测试通过");
        }
    }

    @Nested
    @DisplayName("创建用户测试")
    class CreateUserTests {
        @Test
        @DisplayName("创建单个用户 - 成功")
        void createUser_ShouldReturnSavedUser() {
            log.info("开始测试创建单个用户");
            // 配置 mock 行为
            when(userRepository.save(any(User.class))).thenReturn(user);

            // 执行测试
            User result = userServiceImpl.createUser(userDTO);

            // 验证结果
            assertAll(
                () -> assertNotNull(result, "返回的用户对象不应为空"),
                () -> assertEquals(userDTO.getName(), result.getUserName(), "用户名应该匹配"),
                () -> assertEquals(userDTO.getEmail(), result.getEmail(), "邮箱应该匹配")
            );
            verify(userRepository, times(1)).save(any(User.class));
            log.info("创建用户测试通过");
        }
    }

    @Nested
    @DisplayName("查询用户测试")
    class GetUserTests {
        @Test
        @DisplayName("根据ID查询用户 - 用户存在")
        void getUserById_WhenUserExists_ShouldReturnUser() {
            log.info("开始测试根据ID查询用户 - 用户存在");
            // 配置 mock 行为
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));

            // 执行测试
            User result = userServiceImpl.getUserById(1L);

            // 验证结果
            assertAll(
                () -> assertNotNull(result, "返回的用户对象不应为空"),
                () -> assertEquals(user.getId(), result.getId(), "用户ID应该匹配"),
                () -> assertEquals(user.getUserName(), result.getUserName(), "用户名应该匹配")
            );
            log.info("查询用户测试通过");
        }

        @Test
        @DisplayName("根据ID查询用户 - 用户不存在")
        void getUserById_WhenUserNotExists_ShouldThrowException() {
            log.info("开始测试根据ID查询用户 - 用户不存在");
            // 配置 mock 行为
            when(userRepository.findById(1L)).thenReturn(Optional.empty());

            // 执行测试并验证异常
            EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> userServiceImpl.getUserById(1L),
                "应该抛出 EntityNotFoundException"
            );
            assertEquals("用户不存在", exception.getMessage());
            log.info("用户不存在异常测试通过");
        }

        @Test
        @DisplayName("获取所有用户")
        void getAllUsers_ShouldReturnAllUsers() {
            log.info("开始测试获取所有用户");
            // 配置 mock 行为
            List<User> users = Arrays.asList(user);
            when(userRepository.findAll()).thenReturn(users);

            // 执行测试
            List<User> result = userServiceImpl.getAllUsers();

            // 验证结果
            assertAll(
                () -> assertNotNull(result, "返回的用户列表不应为空"),
                () -> assertEquals(1, result.size(), "应该返回一个用户"),
                () -> assertEquals(user.getUserName(), result.get(0).getUserName(), "用户名应该匹配")
            );
            log.info("获取所有用户测试通过");
        }
    }

    @Nested
    @DisplayName("更新用户测试")
    class UpdateUserTests {
        @Test
        @DisplayName("更新用户信息 - 成功")
        void update_WhenUserExists_ShouldReturnUpdatedUser() {
            log.info("开始测试更新用户信息");
            // 配置 mock 行为
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(userRepository.save(any(User.class))).thenReturn(user);

            // 执行测试
            User result = userServiceImpl.update(1L, userDTO);

            // 验证结果
            assertAll(
                () -> assertNotNull(result, "返回的用户对象不应为空"),
                () -> assertEquals(userDTO.getName(), result.getUserName(), "用户名应该更新"),
                () -> assertEquals(userDTO.getEmail(), result.getEmail(), "邮箱应该更新")
            );
            verify(userRepository, times(1)).save(any(User.class));
            log.info("更新用户信息测试通过");
        }
    }

    @Nested
    @DisplayName("删除用户测试")
    class DeleteUserTests {
        @Test
        @DisplayName("删除用户")
        void deleteUser_ShouldCallRepositoryDelete() {
            log.info("开始测试删除用户");
            // 执行测试
            userServiceImpl.deleteUser(1L);

            // 验证 repository 的 deleteById 方法被调用
            verify(userRepository, times(1)).deleteById(1L);
            log.info("删除用户测试通过");
        }
    }
} 