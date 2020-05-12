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

    Integer updateUserEventNumber(@Param("userId") Long userId);

    Integer updateUserEventFinishedNumber(@Param("userId") Long userId);

    Integer updateUserTagNumber(@Param("userId") Long userId);

    Integer lessUserEventNumber(@Param("userId")Long userId);

    Integer lessUserEventFinishedNumber(@Param("userId") Long userId);

    Integer lessUserTagNumber(@Param("userId") Long userId);
}
