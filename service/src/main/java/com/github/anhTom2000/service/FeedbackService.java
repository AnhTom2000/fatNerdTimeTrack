package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;

/**
 * @author Weleness
 * @date 2020/05/23
 * @description TODO
 */
public interface FeedbackService {

    ResultDTO submitFeedback(String type,String feedbackContent);
}
