package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dao.UserMapper;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.UserDTO;
import com.github.anhTom2000.entity.User;
import com.github.anhTom2000.service.CookieService;
import com.github.anhTom2000.service.UserService;
import com.github.anhTom2000.service.VerifycationService;
import com.github.anhTom2000.utils.BeanConvert.BeanConvertUtil;
import com.github.anhTom2000.utils.Generator.IdGenerator;
import com.github.anhTom2000.utils.Generator.impl.SnowflakeIdGenerator;
import com.github.anhTom2000.utils.encryptUtils.EncryptUtil;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.*;

/**
 * @Description : TODO   用户服务的实现类
 * @Author : Weleness
 * @Date : 2020/05/02
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private VerifycationService verifycationService;

    private final IdGenerator idGenerator = SnowflakeIdGenerator.getInstance();

    @Override
    public ResultDTO register(String username, String password, String email, String verification, HttpServletRequest request, HttpServletResponse response) {
        ResultDTO dto = verifycationService.checkEmailVerifyCode(email, verification);
        User user = null;
        if (dto.isStatus()) {
            user = User.builder().userId(idGenerator.generateId()).username(username).password(EncryptUtil.generate(password)).email(email).createTime(LocalDateTime.now(Clock.systemDefaultZone())).build();
            if (userMapper.register(user) > 0) {
                dto.setMessage("注册成功");
                Cookie cookie = cookieService.generateRandomCookie(COOKIE_SEESION_KEY);
                HttpSession session = request.getSession();
                session.setAttribute(cookie.getValue(), user.getUserId());
                session.setMaxInactiveInterval(TimeOut);
                response.addCookie(cookie);
            }
        }
        return dto;
    }

    @Override
    public UserDTO findUserById(Long userId) {
        User user = userMapper.findUserById(userId);
        return user == null ? null : BeanConvertUtil.create(user, UserDTO.class);
    }

    @Override
    public ResultDTO login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper.findUserByName(username);
        ResultDTO result = null;
        if (user != null) {
            if (!Objects.isNull(username) && !Objects.isNull(password)) {
                if (EncryptUtil.verify(password, user.getPassword())) {
                    Cookie cookie = cookieService.generateRandomCookie(COOKIE_SEESION_KEY);
                    HttpSession session = request.getSession();
                    session.setAttribute(cookie.getValue(), user.getUserId());
                    session.setMaxInactiveInterval(TimeOut);
                    response.addCookie(cookie);
                    result = new ResultDTO(Httpcode.OK_CODE.getCode(), "登陆成功", true);
                } else result = new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "密码错误", false);
            } else result = new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "账号或密码不能为空", false);
        } else result = new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "账号不存在", false);
        return result;
    }

    @Override
    public Integer updateUserEventNumber(Long userId) {
        return userMapper.updateUserEventNumber(userId);
    }

    @Override
    public Integer updateUserEventFinishedNumber(Long userId) {
        return userMapper.updateUserEventFinishedNumber(userId);
    }

    @Override
    public Integer updateUserTagNumber(Long userId) {
        return userMapper.updateUserTagNumber(userId);
    }

    @Override
    public Integer lessUserEventNumber(Long userId) {
        return userMapper.lessUserEventNumber(userId);
    }

    @Override
    public Integer lessUserEventFinishedNumber(Long userId) {
        return userMapper.lessUserEventFinishedNumber(userId);
    }

    @Override
    public Integer lessUserTagNumber(Long userId) {
        return userMapper.lessUserTagNumber(userId);
    }
}
