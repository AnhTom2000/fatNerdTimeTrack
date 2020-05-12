package com.github.anhTom2000.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description : TODO  cookie 服务
 * @Author : Weleness
 * @Date : 2020/05/03
 */
public interface CookieService {

    Cookie getCookie(String key, HttpServletRequest request);

    Cookie generateRandomCookie(String key);

    void removeCookie(Cookie cookie, HttpServletResponse response);

}
