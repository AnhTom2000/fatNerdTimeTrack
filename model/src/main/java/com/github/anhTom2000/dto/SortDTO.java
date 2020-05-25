package com.github.anhTom2000.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/23
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortDTO implements Serializable {

    private static final long serialVersionUID = 6546414748233252229L;

    private Integer value;
}
