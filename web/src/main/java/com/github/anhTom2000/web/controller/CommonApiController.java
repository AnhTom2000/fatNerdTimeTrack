package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.annotation.Action;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.SortDTO;
import com.github.anhTom2000.dto.UserDTO;
import com.github.anhTom2000.service.*;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Lazy
    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Qualifier("sortService")
    @Autowired
    private SortService sortService;

    @RequestMapping("/send/email/checkCode")
    public ResultDTO sendVerification(
            @NotBlank(message = "邮箱不能为空")
            @Length(max = 32, message = "邮箱不能超过32位")
            @Email(message = "邮箱格式不正确")
            @RequestParam("email") String email) {
        return verifycationService.sendEmailWithVerifyCode(email);
    }

    @Action("DeleteEventTag")
    @RequestMapping("/update/tag/deleteEventTag")
    public ResultDTO DeleteEventTag(@RequestParam("tagId") Long tagId) {
        return event_tagService.deleteInMiddle(tagId);
    }

    @Action("updateUserAvator")
    @RequestMapping(value = "/update/user/updateUserAvator", method = RequestMethod.POST)
    public ResultDTO updateUserAvator(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);
        Long userId = null;
        UserDTO userDTO = null;
        HttpSession session = request.getSession();
        return uploadService.uploadUserAvator((Long) session.getAttribute(cookie.getValue()), file, request);
    }


    @RequestMapping("/check/findUser")
    public ResultDTO findUser(
            @NotBlank(message = "用户名不能为空")
            @Length(max = 30, message = "用户名不能超过30个字符")
            @RequestParam("name") String name
    ) {
        return userService.findUser(name);
    }

    @RequestMapping("/exit/userExit")
    public ResultDTO exit(HttpServletRequest request, HttpServletResponse response) {
        cookieService.removeCookie(cookieService.getCookie(COOKIE_SEESION_KEY, request), response);
        return ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message(null).status(true).build();
    }

    @Action("geiSort")
    @RequestMapping("/sort/getSort")
    public SortDTO getSort(HttpServletRequest request){
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request);
        long userId = (long) request.getSession().getAttribute(cookie.getValue());
        return sortService.getSort(userId);
    }

    @Action("updateSort")
    @RequestMapping("/sort/updateSort")
    public SortDTO updateSort(@RequestParam("sortNumber") Integer sortNumber,HttpServletRequest request){
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request);
        Long userId = (Long) request.getSession().getAttribute(cookie.getValue());
        return sortService.updateSort(sortNumber,userId);
    }
}
