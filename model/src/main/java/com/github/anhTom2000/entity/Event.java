package com.github.anhTom2000.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sun.org.apache.bcel.internal.generic.FADD;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description : TODO  事件实体类
 * @Author : Weleness
 * @Date : 2020/05/01
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(value = {"handler"})
public class Event implements Serializable {

    private static final long serialVersionUID = 8937743696113806228L;

    // 事件id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long eventId;

    // 事件标题
    @JsonProperty(value = "eventTitle")
    @NotBlank(message = "标题不能为空")
    private String eventTitle;

    // 事件描述
    @JsonProperty(value = "eventDescription")
    private String eventDescription;

    // 这个事件属于哪个用户
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @JsonProperty("tagList")
    private List<Tag> tagList;

    // 事件优先级
    @JsonProperty("priority")
    private Integer priorityId;

    // 事件是否完成
    @JsonProperty("finished")
    private Boolean finished;

    // 事件完成日期，可选
    @JsonProperty("date")
    private LocalDateTime date;


    // 事件完成时间
    private LocalDateTime endDate;

}
