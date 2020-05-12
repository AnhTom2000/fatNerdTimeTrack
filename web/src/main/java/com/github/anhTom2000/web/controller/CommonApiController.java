package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.service.EmailService;
import com.github.anhTom2000.service.Event_TagService;
import com.github.anhTom2000.service.VerifycationService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/03
 */
@RequestMapping("/api")
@RestController
@Validated
public class CommonApiController {

    @Qualifier("verifycationService")
    @Autowired
    private VerifycationService verifycationService;

    @Qualifier("eventTagService")
    @Autowired
    private Event_TagService event_tagService;

    @CrossOrigin
    @RequestMapping("/send/email/checkCode")
    public ResultDTO sendVerification(
            @NotBlank(message = "邮箱不能为空")
            @Length(max = 32, message = "邮箱不能超过32位")
            @Email(message = "邮箱格式不正确")
            @RequestParam("email") String email) {
        System.out.println(email);
        return verifycationService.sendEmailWithVerifyCode(email);
    }

    @RequestMapping("/update/tag/deleteEventTag")
    public ResultDTO DeleteEventTag(@RequestParam("tagId") Long tagId ){
        return event_tagService.deleteInMiddle(tagId);
    }
}
