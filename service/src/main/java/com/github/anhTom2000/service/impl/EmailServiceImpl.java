package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.service.EmailService;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Description : TODO  邮箱服务的实现类
 * @Author : Weleness
 * @Date : 2020/05/02
 */
@PropertySource("classpath:application.properties")
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.subject}")
    private String subject;
    private String htmlContent = "<html>\n" + "<body>\n"
            + "<h3>您的验证码是&nbsp;%s,请在120秒内完成操作</h3>\n"
            + "</body>\n" + "</html>";

    @Value("${mail.from}")
    private String myself;


    @Override
    public void sendHtmlMail(String to, String verifyCode) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(String.format(htmlContent,verifyCode), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultDTO sendFeedback(String type, String feedbackContent) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            String content = "<html>\n" + "<body>\n"
                    + "<h3>类型："+type+"</h3>\n"
                    + "<h3>内容："+feedbackContent+"</h3>\n"
                    + "</body>\n" + "</html>";
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(from);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("发送成功").status(true).build();
    }
}
