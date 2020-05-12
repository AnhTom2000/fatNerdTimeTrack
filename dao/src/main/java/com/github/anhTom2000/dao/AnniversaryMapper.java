package com.github.anhTom2000.dao;

import com.github.anhTom2000.entity.Anniversary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author Weleness
 * @date 2020/05/10
 * @description TODO
 */
@Repository("anniversaryMapper")
public interface AnniversaryMapper {

    List<Anniversary> findAllAnniversary(@Param("userId") Long userId);

    Integer insertAnniversary(@Param("anniversary") Anniversary anniversary, @Param("userId") Long userId);

    Integer updateAnniversary(@Param("anniversary") Anniversary anniversary);

    void deleteAnniversary(@Param("anniversaryId") Long anniversaryId);
}
