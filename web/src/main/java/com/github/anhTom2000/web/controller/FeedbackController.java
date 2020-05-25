package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.annotation.Action;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/23
 */
@RequestMapping("/api/feedback")
@RestController
@Validated
public class FeedbackController {

    @Qualifier("feedbackService")
    @Autowired
    private FeedbackService feedbackService;

    @Action("submitFeedback")
    @RequestMapping("/submitFeedback")
    public ResultDTO submitFeedback(@RequestParam("type") @NotBlank(message = "类型不能为空") String type
            , @RequestParam("feedbackContent") @NotBlank(message = "反馈内容不能为空") String feedbackContent) {
        return feedbackService.submitFeedback(type, feedbackContent);
    }

}
