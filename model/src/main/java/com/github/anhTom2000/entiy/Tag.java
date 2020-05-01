package com.github.anhTom2000.entiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"handler"})
public class Tag implements Serializable {

    private static final long serialVersionUID = -8262304461885904420L;

    // 标签id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tag_id;

    // 标签名
    private String tagName;

    // 标签颜色
    private String tagBgColor;

    // 这个标签属于哪个用户
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
}
