package com.github.anhTom2000.dao;

import com.github.anhTom2000.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description : TODO   事件标签中间表dao层映射
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@Repository("eventTagMapper")
public interface Event_TagMapper {

    List<Tag> findInMiddle(@Param("eventId") Long eventId);

    Integer insertInMiddle(@Param("eventId") Long eventId,@Param("tagList") List<Tag> tagList);

    void deleteEventTag(@Param("tagId") Long tagId);

    void deleteTag(@Param("eventId")Long eventId);

    void deleteOneEventTag(@Param("eventId")Long eventId,@Param("tagId")Long tagId);
}
