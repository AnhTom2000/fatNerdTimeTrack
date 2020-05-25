package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.SortDTO;
import com.github.anhTom2000.service.SortService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/23
 */
@Service("sortService")
public class SortServiceImpl implements SortService {

    @Cacheable(cacheNames = "sort",key = "#userId")
    @Override
    public SortDTO getSort(Long userId) {
        return SortDTO.builder().value(0).build();
    }

    @CachePut(cacheNames="sort",key = "#userId")
    @Override
    public SortDTO updateSort(Integer sortNumber, Long userId) {
        return new SortDTO(sortNumber);
    }
}
