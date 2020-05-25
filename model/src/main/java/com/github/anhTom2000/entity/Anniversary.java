package com.github.anhTom2000.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;
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
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class Anniversary implements Serializable {

    private static final long serialVersionUID = -998139897098103554L;

    // 纪念日id
    @JsonSerialize(using = ToStringSerializer.class)
    Long anniversaryId;

    @JsonSerialize(using = ToStringSerializer.class)
    Long userId;

    @JsonProperty(value = "anniversaryTitle")
    @NotBlank(message = "纪念日标题不能为空")
    String anniversaryTitle;

    @JsonProperty(value = "anniversaryTime")
    @NotBlank(message = "纪念日日期不能为空")
    String anniversaryTime;

    @JsonProperty(value = "anniversaryDescription")
    String anniversaryDescription;
}
