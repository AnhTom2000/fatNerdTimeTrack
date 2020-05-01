package com.github.anhTom2000.entiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description : TODO  纪念日实体类
 * @Author : Weleness
 * @Date : 2020/05/01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"handler"})
public class Anniversary implements Serializable {

    private static final long serialVersionUID = -998139897098103554L;

    // 纪念日id
    @JsonSerialize(using = ToStringSerializer.class)
    public Long anniversaryId;

    // 纪念日属于哪个用户
    @JsonSerialize(using = ToStringSerializer.class)
    public Long userId;

    // 纪念日标题
    public String anniversaryTitle;

    // 纪念日时间
    public LocalDateTime anniversaryTime;

    // 纪念日描述
    public String anniversaryDescription;
}
