package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.AnniversaryDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Anniversary;

import java.sql.Date;
import java.util.List;

/**
 * @Description : TODO  纪念日服务
 * @Author : Weleness
 * @Date : 2020/05/11
 */
public interface AnniversaryService {

    List<AnniversaryDTO> findAllAnniversary(Long userId);

    AnniversaryDTO insertAnniversary(Anniversary anniversary, Long userId);

    ResultDTO updateAnniversary(Anniversary anniversary);


    ResultDTO deleteAnniversary( Long anniversaryId);
}
