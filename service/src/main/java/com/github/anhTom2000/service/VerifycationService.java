package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;

/**
 * @author Weleness
 * @date 2020/05/02
 * @description TODO  验证码服务
 */
public interface VerifycationService {

    ResultDTO sendEmailWithVerifyCode(String email);


    ResultDTO checkEmailVerifyCode(String email, String verifyCode);
}
