package com.github.anhTom2000.aop;


import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.exceptions.UnLoginException;
import com.github.anhTom2000.service.CookieService;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


import static com.github.anhTom2000.config.redis.RestTemplateConfig.COOKIE_SEESION_KEY;

/**
 * Created on 15:26  23/02/2020
 * Description:
 *
 * @author Weleness
 */
@Aspect
@Component
public class LoginAop {
    @Autowired
    @Qualifier("cookieService")
    private CookieService cookieService;

    @Pointcut("@annotation(com.github.anhTom2000.annotation.Action)")
    public void login() {
    }

    @Pointcut("@annotation(com.github.anhTom2000.annotation.BeforeSth)")
    public void artilceDST() {
    }

    @Around("artilceDST()")
    public Object principalAround(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Object[] args = pjp.getArgs();
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);
        ResultDTO result = null;
        if (cookie == null) {
            result = ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("请先登陆").status(false).build();
        } else {
            HttpSession session = request.getSession();
            if ((session.getAttribute(cookie.getValue())) == null) {
                //if(pjp.getSignature())
                result = ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("请先登陆").status(false).build();
            } else {
                return pjp.proceed(args);
            }
        }
        return result;
    }

    @Before("login()")
    public void dstBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);

        if (cookie == null) {
            throw new UnLoginException("请先登录");
        } else {
            HttpSession session = request.getSession();
            if ((session.getAttribute(cookie.getValue())) == null) {
                throw new UnLoginException("请先登录");
            }
        }
    }
}
