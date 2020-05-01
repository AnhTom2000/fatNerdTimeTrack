package com.github.anhTom2000.entiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"handler"})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 6764700468505401360L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long feedbackId;

    private String feedbackTitle;

    private String feedbackContent;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private Boolean type;

    private Boolean isReply;

    private String replyContent;
}
