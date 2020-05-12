package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.service.EmailService;
import com.github.anhTom2000.service.VerifycationService;
import com.github.anhTom2000.utils.Generator.CodeGenerator;
import com.github.anhTom2000.utils.Generator.impl.CodeGeneratorImpl;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import com.mysql.cj.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description : TODO   验证码服务实现类
 * @Author : Weleness
 * @Date : 2020/05/02
 */
@Service("verifycationService")
public class VerifycationServiceImpl implements VerifycationService {

    @Qualifier("emailService")
    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final CodeGenerator CODE_GENERATOR = new CodeGeneratorImpl();

    private static final Long TIME_OUT = 120L;

    @Override
    public ResultDTO sendEmailWithVerifyCode(String email) {
        ResultDTO result = null;
        try {
            String verification = CODE_GENERATOR.generateCode();
            // 将验证码存入缓存
            redisTemplate.opsForValue().set(email, verification, TIME_OUT, TimeUnit.SECONDS);
            // 发送验证码邮件
            emailService.sendHtmlMail(email, verification);
            result = ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("邮件发送成功，请在邮箱中查看").status(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultDTO.builder().code(Httpcode.SERVER_ERROR_CODE.getCode()).message("服务端错误").status(false).build();
        }
        return result;
    }


    @Override
    public ResultDTO checkEmailVerifyCode(String email, String verifyCode) {
        ResultDTO result = null;
        String verifyCodeInCache = (String) redisTemplate.opsForValue().get(email);
        if (!Objects.isNull(verifyCodeInCache)) {
            if (Objects.equals(verifyCode, verifyCodeInCache)) {
                result = new ResultDTO(Httpcode.OK_CODE.getCode(), "验证成功", true);
            } else {
                result = new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "验证码不正确", false);
            }
        } else {
            result = new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "验证码不存在", false);
        }
        return result;
    }
}
