package com.github.anhTom2000.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.anhTom2000.entity.Tag;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description : TODO  事件DTO类
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class EventDTO implements Serializable {

    private static final long serialVersionUID = -8028955875915554889L;

    // 事件id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long eventId;

    // 事件标题
    private String eventTitle;

    // 事件描述
    private String eventDescription;

    // 这个事件属于哪个用户
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    // 事件优先级
    private Integer priorityId;

    private List<Tag> tagList;

    // 事件是否完成
    private Boolean finished;

    // 事件完成日期，可选
    private LocalDateTime date;

    // 事件完成时间
    private LocalDateTime endDate;

}
