package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.SortDTO;

/**
 * @author Weleness
 * @date 2020/05/23
 * @description TODO
 */
public interface SortService {

    SortDTO getSort(Long userId);

    SortDTO updateSort(Integer sortNumber,Long userId);
}
