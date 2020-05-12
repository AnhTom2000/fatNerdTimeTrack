package com.github.anhTom2000.dto;


import lombok.*;

/**
 * @Description : TODO  无对象需要返回时的dto类
 * @Author : Weleness
 * @Date : 2020/05/02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public  class  ResultDTO {

    private Integer code;
    private String message;
    private boolean status;


}
