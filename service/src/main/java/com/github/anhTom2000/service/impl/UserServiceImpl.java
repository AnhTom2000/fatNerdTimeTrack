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
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
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
        ResultDTO result = null;
        try {
            User user = userMapper.findUserByName(username);
            if (user == null) throw new NotFoundException("账号不存在");
            if (Objects.isNull(username) && Objects.isNull(password)) {
                throw new NullPointerException("账号或密码不能为空");
            }
            if (EncryptUtil.verify(password, user.getPassword())) {
                Cookie cookie = cookieService.generateRandomCookie(COOKIE_SEESION_KEY);
                HttpSession session = request.getSession();
                session.setAttribute(cookie.getValue(), user.getUserId());
                session.setMaxInactiveInterval(TimeOut);
                response.addCookie(cookie);
                result = new ResultDTO(Httpcode.OK_CODE.getCode(), "登陆成功", true);
            } else result = new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "密码错误", false);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public void updateUserAvator(String avator, Long userId) {
        userMapper.updateUserAvator(avator, userId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO bind(String bind, String flag, Long userId) {
        ResultDTO resultDTO = null;
        try {
            if (flag == null) throw new NotFoundException("未找到要绑定的标识");
            resultDTO = ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("绑定成功").status(true).build();
            switch (flag) {
                case "phone":
                    userMapper.bind(bind, null, null, userId);
                    break;
                case "qq":
                    userMapper.bind(null, bind, null, userId);
                    break;
                case "description":
                    userMapper.bind(null, null, bind, userId);
                    break;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return resultDTO;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO bindEmail(String checkCode, String email, Long userId) {
        ResultDTO resultDTO = verifycationService.checkEmailVerifyCode(email, checkCode);
        if (resultDTO.isStatus()) {
            userMapper.bindEmail(email, userId);
        }
        return resultDTO;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO changePassword(String name, String email, String checkCode, String password) {
        ResultDTO resultDTO = verifycationService.checkEmailVerifyCode(email, checkCode);
        if (resultDTO.isStatus()) {
            User user = userMapper.findUserByNameAndEmail(email, name);
            if (user != null) {
                userMapper.changePassword(EncryptUtil.generate(password), user.getUserId());
            } else {
                resultDTO.setCode(Httpcode.CLIENT_ERROR_CODE.getCode());
                resultDTO.setMessage("找不到此用户");
                resultDTO.setStatus(false);
            }
        }
        return resultDTO;
    }

    @Override
    public ResultDTO findUser(String name) {
        return userMapper.findUserByName(name) != null ? ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("账号已注册").status(true).build() : ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message(null).status(false).build();
    }


}
