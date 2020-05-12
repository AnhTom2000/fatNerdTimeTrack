package com.github.anhTom2000.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @Description : TODO  标签实体类
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
public class Tag implements Serializable {

    private static final long serialVersionUID = -8262304461885904420L;

    // 标签id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    // 标签名
    @JsonProperty(value = "tagName")
    private String tagName;


    @JsonProperty(value = "selected")
    private Boolean selected;

    // 标签颜色
    @JsonProperty(value = "tagBgColor")
        private String tagBgColor;

}
