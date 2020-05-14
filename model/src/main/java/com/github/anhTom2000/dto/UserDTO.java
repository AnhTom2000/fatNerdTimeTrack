package com.github.anhTom2000.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/02
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"handler"})
public class UserDTO implements Serializable {


    private static final long serialVersionUID = -1861616397557863389L;

    // 用户id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    // 用户名

    private String username;

    // 用户密码
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
    private String email;

    private String phone;


    private String qq;
    // 用户自我描述
    private String description;

    // 用户账户创建时间
    private LocalDateTime createTime;


}

