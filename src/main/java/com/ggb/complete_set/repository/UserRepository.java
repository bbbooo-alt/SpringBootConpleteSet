package com.ggb.complete_set.repository;

import com.ggb.complete_set.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 用户数据访问接口
@Repository// 标识这是一个数据访问层组件
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    //1.继承JpaRepository
    //继承JpaRepository，类就有了基本的crud
    //《user， long》表示实体类型和主键类型
    //2.继承JpaSpecificationExecutor，类就能完成更复杂的动态查询

    //定义简单的查询方法
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findByStatus(String status);

    Optional<User> findByUsernameAndStatus(String username, String status);
    //Optional表示返回单个对象
    //List表示返回列表


    //复杂的查询
    // 使用JPQL查询语言自定义查询
    //@Query表示这是自定义查询
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    //@Param表示数据库中的某个属性和后续的参数相对应
    List<User> searchUsers(@Param("keyword") String keyword);

    // 使用原生SQL查询
    //nativeQuery = true，表示把前面的sql是原生sql语句，通过nativeQuery = true将其转化成JPQL查询语言
    @Query(value = "SELECT * FROM users WHERE status = :status", nativeQuery = true)
    List<User> findByStatusNative(@Param("status") Integer status);

    // @Modifying表示这是一条更新语句
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    //表示更改的行数
    int updateUserStatus(@Param("id") Long id, @Param("status") Integer status);


    //分页查询
    //返回的page类型就是一个分页，Pageable类就是相当于制定分页和排序规则的，具体创建和用法自己搜
    Page<User> findByStatus(Integer status, Pageable pageable);

    //排序查询
    //就是在方法名加上OrderBY。。At。。
    List<User> findByStatusOrderByIdAtDesc(Integer status);

    //排序+分页
    Page<User> findByStatusOrderByIdAtDesc(Integer status, Pageable pageable);

    //    这个接口不需要自己写实现类就能使用其方法，原因:
    //    Spring Data JPA会在运行时自动生成实现类
    //    通过方法名自动生成对应的SQL查询
    //    不需要手动编写实现代码

    //default允许接口实现自己的方法，在实现类中就不必强制实现，主要用于逻辑相同重复率很高的逻辑。
}
