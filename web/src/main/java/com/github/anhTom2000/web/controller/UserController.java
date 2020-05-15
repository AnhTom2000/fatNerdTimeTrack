package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.annotation.Action;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.UserDTO;
import com.github.anhTom2000.service.CookieService;
import com.github.anhTom2000.service.UserService;
import com.github.anhTom2000.service.VerifycationService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.Arrays;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.*;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/02
 */
@RequestMapping("/api/user")
@RestController
@Validated
public class UserController {

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Qualifier("cookieService")
    @Autowired
    private CookieService cookieService;


    @PostMapping("/register")
    public ResultDTO register(@Pattern(regexp = "^\\D+?.*$", message = "用户名不能以数字开头")
                              @Length(max = 15, message = "用户名长度必须在1-15位之间")
                              @NotBlank(message = "用户名不能为空")
                              @RequestParam("username")
                                      String username,
                              @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,20}$", message = "密码必须由7-20位大小写英文字母,数字(允许中文和特殊字符)组成")
                              @NotBlank(message = "密码不能为空")
                              @RequestParam("password")
                                      String password,
                              @Email(message = "邮箱格式不正确")
                              @NotBlank(message = "邮箱不能为空")
                              @Length(max = 32, message = "邮箱不能超过32位")
                              @RequestParam("email")
                                      String email,
                              @NotBlank(message = "验证码不能为空")
                              @Length(min = 6, message = "验证码不能小于6位")
                              @RequestParam("verification") String veriyfication,
                              HttpServletRequest request,
                              HttpServletResponse response
    ) {
        return userService.register(username, password, email, veriyfication, request, response);
    }

    @RequestMapping("/isLogin")
    public UserDTO isLogin(HttpServletRequest request) {
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);
        Long userId = null;
        UserDTO userDTO = null;
        if (cookie != null) {
            HttpSession session = request.getSession();
            if ((userId = (Long) session.getAttribute(cookie.getValue())) != null) {
                userDTO = userService.findUserById(userId);
            }
        }
        return userDTO;
    }

    @RequestMapping("/login")
    public ResultDTO login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        return userService.login(username, password, request, response);
    }

    @Action("bind")
    @RequestMapping("/bind")
    public ResultDTO bind(@RequestParam("bind") String bind, @RequestParam("flag") String flag, HttpServletRequest request) {
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);
        HttpSession session = request.getSession();
        return  userService.bind(bind, flag, (Long) session.getAttribute(cookie.getValue()));
    }

    @Action("bindEmail")
    @RequestMapping("/bind/bindEmail")
    public ResultDTO bindEmail(@RequestParam("checkCode") String checkCode, String email, HttpServletRequest request) {
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);
        Long userId = null;
        ResultDTO resultDTO = null;
        if (cookie != null) {
            HttpSession session = request.getSession();
            if ((userId = (Long) session.getAttribute(cookie.getValue())) != null) {
                resultDTO = userService.bindEmail(checkCode, email, userId);
            }
        }
        return resultDTO;
    }

    @PostMapping("/changePassword")
    public ResultDTO changPassword(@Pattern(regexp = "^\\D+?.*$", message = "用户名不能以数字开头")
                                   @Length(max = 15, message = "用户名长度必须在1-15位之间")
                                   @NotBlank(message = "用户名不能为空")
                                   @RequestParam("name") String name,
                                   @NotBlank(message = "邮箱不能为空")
                                   @Email(message = "邮箱格式不正确")
                                   @Length(max = 32, message = "邮箱不能超过32位")
                                   @RequestParam("email") String email,
                                   @NotBlank(message = "验证码不能为空")
                                   @Length(min = 6, max = 6, message = "验证码长度必须是6位")
                                   @RequestParam("checkCode") String checkCode,
                                   @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,20}$", message = "密码必须由7-20位大小写英文字母,数字(允许中文和特殊字符)组成")
                                   @NotBlank(message = "密码不能为空")
                                   @RequestParam("password") String password) {
        ResultDTO resultDTO = null;
        resultDTO = userService.changePassword(name, email, checkCode, password);
        return resultDTO;
    }


}
