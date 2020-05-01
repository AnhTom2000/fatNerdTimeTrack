package com.github.anhTom2000.entiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description : TODO  事件实体类
 * @Author : Weleness
 * @Date : 2020/05/01
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"handler"})
public class Event implements Serializable {

    private static final long serialVersionUID = 8937743696113806228L;

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

    // 事件是否完成
    private Boolean finished;

    // 事件完成事件，可选
    private LocalDateTime time;

}
