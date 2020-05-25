package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;

/**
 * @Description : TODO  邮箱服务
 * @Author : Weleness
 * @Date : 2020/05/02
 */
public interface EmailService {

    void  sendHtmlMail(String to,String verifyCode);

    ResultDTO sendFeedback(String type,String feedbackContent);
}
