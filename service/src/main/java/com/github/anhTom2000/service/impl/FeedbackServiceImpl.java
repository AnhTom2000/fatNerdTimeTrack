package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.service.EmailService;
import com.github.anhTom2000.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/23
 */

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Qualifier("emailService")
    @Autowired
    private EmailService emailService;

    @Override
    public ResultDTO submitFeedback(String type, String feedbackContent) {
        return emailService.sendFeedback(type, feedbackContent);
    }
}
