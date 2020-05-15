package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.service.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.TimeOut;

/**
 * @Description : TODO   cookie服务
 * @Author : Weleness
 * @Date : 2020/05/03
 */

@Service("cookieService")
public class CookieServiceImpl implements CookieService {


    @Override
    public Cookie generateRandomCookie(String key) {
        if (key == null) {
            return null;
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Cookie cookie = new Cookie(key, uuid);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(TimeOut);
        return cookie;
    }


    @Override
    public void removeCookie(Cookie cookie, HttpServletResponse response) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    @Override
    public Cookie getCookie(String key, HttpServletRequest request) {
        if (request.getCookies() != null && key != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().endsWith(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
