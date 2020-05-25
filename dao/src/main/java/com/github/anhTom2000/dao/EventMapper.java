package com.github.anhTom2000.dao;

import com.github.anhTom2000.entity.Event;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Weleness
 * @date 2020/05/04
 * @description TODO   事件dao层映射类
 */
@Repository("eventMapper")
public interface EventMapper {

    Integer addEvent(@Param("event") Event event);

    List<Event> findAllEventByUserId(@Param("userId") Long userId);

    Integer updateEventTitle(@Param("eventId") Long eventId, @Param("eventTitle") String eventTitle);

    Integer updateDescription(@Param("eventDescription") String description, @Param("eventId") Long eventId);

    Integer updateEventFinished(@Param("eventId") Long eventId);

    Integer updateEventDate(@Param("date") LocalDateTime date, @Param("eventId") Long eventId);

    Integer updateEventPriority(@Param("priorityId")Integer priorityId , @Param("eventId") Long eventId);

    Integer updateEventEndTime(@Param("endTime")LocalDateTime endTime,@Param("eventId") Long eventId);

    //Integer updateEventTag(@Param(""))
    void deleteEvent(@Param("eventId") Long eventId);
}
