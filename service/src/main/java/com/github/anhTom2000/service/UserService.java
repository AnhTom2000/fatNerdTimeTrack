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

    void updateUserAvator(String avator, Long userId);

    ResultDTO bind(String bind, String flag, Long userId);

    ResultDTO bindEmail(String checkCode,String email,Long userId);

    ResultDTO changePassword(String name,String email,String checkCode,String password);

    ResultDTO updateUsername(String name,Long userId);

    ResultDTO findUser(String name);


}
