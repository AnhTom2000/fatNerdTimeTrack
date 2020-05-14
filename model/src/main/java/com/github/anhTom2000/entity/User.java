package com.github.anhTom2000.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description : TODO  用户实体类
 * @Author : Weleness
 * @Date : 2020/05/01
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {

    private static final long serialVersionUID = -8307861996462450474L;

    // 用户id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    // 用户名
    @Pattern(regexp = "^\\D+?.*$",message = "用户名不能以数字开头")
    @Length(max=15,message = "用户名长度必须在1-15位之间")
    @NotBlank(message = "用户名不能为空")
    private String username;

    // 用户密码
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,20}$",message = "密码必须由7-20位大小写英文字母,数字(允许中文和特殊字符)组成")
    @NotBlank(message = "密码不能为空")
    private String password;

    // 用户头像地址
    private String avatarUrl;

    // 用户事件数量
    private Integer eventCount;

    // 用户标签数量
    private Integer tagCount;

    // 用户完成事件数量
    private Integer event_finishedCount;

    // 用户绑定的邮箱
    @Email(message = "邮箱格式不正确", regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    @NotBlank(message = "邮箱不能为空")
    @Length(max = 32,message = "邮箱不能超过32位")
    private String email;


    @Length(max = 11,message = "手机号不能超过11位")
    private String phone;

    private String qq;

    // 用户自我描述
    @Length(max = 255,message = "自我描述不能超过255位")
    private String description;

    // 用户账户创建时间
    private LocalDateTime createTime;


}
