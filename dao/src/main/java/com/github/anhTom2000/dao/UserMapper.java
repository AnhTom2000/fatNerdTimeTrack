package com.github.anhTom2000.dao;

import com.github.anhTom2000.dto.UserDTO;
import com.github.anhTom2000.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Weleness
 * @date 2020/05/02
 * @description TODO
 */
@Repository("userMapper")
public interface UserMapper {

    Integer register(@Param("user") User user);

    User findUserById(@Param("userId") Long userId);

    User findUserByName(@Param("username") String username);

    User findUserByNameAndEmail(@Param("email") String email,@Param("username")String username);

    Integer changePassword(@Param("password") String password, @Param("userId") Long userId);

    Integer updateUsername(@Param("username")String username,@Param("userId")Long userId);

    Integer updateUserAvator(@Param("avator") String avator, @Param("userId") Long userId);

    Integer bind(@Param("phone") String phone, @Param("qq") String qq, @Param("description") String description, @Param("userId") Long userId);

    Integer bindEmail(@Param("email") String email,@Param("userId") Long userId);
}
