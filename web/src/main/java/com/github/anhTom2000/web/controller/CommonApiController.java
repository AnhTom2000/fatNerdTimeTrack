package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.UserDTO;
import com.github.anhTom2000.service.CookieService;
import com.github.anhTom2000.service.Event_TagService;
import com.github.anhTom2000.service.UploadService;
import com.github.anhTom2000.service.VerifycationService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.COOKIE_SEESION_KEY;

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

    @Qualifier("uploadService")
    @Autowired
    private UploadService uploadService;

    @Qualifier("cookieService")
    @Autowired
    private CookieService cookieService;

    @CrossOrigin
    @RequestMapping("/send/email/checkCode")
    public ResultDTO sendVerification(
            @NotBlank(message = "邮箱不能为空")
            @Length(max = 32, message = "邮箱不能超过32位")
            @Email(message = "邮箱格式不正确")
            @RequestParam("email") String email) {
        return verifycationService.sendEmailWithVerifyCode(email);
    }

    @RequestMapping("/update/tag/deleteEventTag")
    public ResultDTO DeleteEventTag(@RequestParam("tagId") Long tagId ){
        return event_tagService.deleteInMiddle(tagId);
    }

    @RequestMapping(value = "/update/user/updateUserAvator",method = RequestMethod.POST)
    public ResultDTO updateUserAvator(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request);
        Long userId = null;
        UserDTO userDTO = null;
        if (cookie != null) {
            HttpSession session = request.getSession();
            if ((userId = (Long) session.getAttribute(cookie.getValue())) != null) {
                return uploadService.uploadUserAvator(userId,file,request);
            }
        }
      return null;
    }
}
