package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Weleness
 * @date 2020/05/02
 * @description TODO   用户服务
 */
public interface UserService {

    ResultDTO register(String username, String password, String email, String verification, HttpServletRequest request, HttpServletResponse response);

    UserDTO findUserById(Long userId);

    ResultDTO login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Integer updateUserEventNumber(Long userId);

    Integer updateUserEventFinishedNumber(Long userId);

    Integer updateUserTagNumber(Long userId);

    Integer lessUserEventNumber(Long userId);

    Integer lessUserEventFinishedNumber(Long userId);

    Integer lessUserTagNumber(Long userId);
}
