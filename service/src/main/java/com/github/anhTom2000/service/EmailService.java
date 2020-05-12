package com.github.anhTom2000.service;

/**
 * @Description : TODO  邮箱服务
 * @Author : Weleness
 * @Date : 2020/05/02
 */
public interface EmailService {

    void  sendHtmlMail(String to,String verifyCode);
}
