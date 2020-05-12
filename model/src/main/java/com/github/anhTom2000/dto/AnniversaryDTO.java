package com.github.anhTom2000.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/10
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class AnniversaryDTO implements Serializable {

    private static final long serialVersionUID = 7300961411029982138L;

    @JsonSerialize(using = ToStringSerializer.class)
    Long anniversaryId;

    @JsonSerialize(using = ToStringSerializer.class)
    Long userId;

    String anniversaryTitle;

    String anniversaryTime;

    String anniversaryDescription;
}
