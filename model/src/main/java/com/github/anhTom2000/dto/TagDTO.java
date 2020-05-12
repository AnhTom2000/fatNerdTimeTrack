package com.github.anhTom2000.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class TagDTO implements Serializable {


    private static final long serialVersionUID = -2767643900206235749L;

    // 标签id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    // 标签名
    private String tagName;

    // 标签颜色
    private String tagBgColor;

    //是否被选择
    private Boolean selected;
}
