package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.EventDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Weleness
 * @date 2020/05/04
 * @description TODO  事件服务
 */
public interface EventService {


    EventDTO addEvent(Event event);

    List<EventDTO> getEvent(Long userId);

    ResultDTO updateEventTitle(String eventTitle,Long eventId);

    ResultDTO updateDescription( String description, Long eventId);

    ResultDTO updateEventFinished(Long eventId);

    ResultDTO updateEventDate(LocalDateTime date,  Long eventId);

    ResultDTO updateEventPriority(Integer priorityId ,  Long eventId);

    ResultDTO deleteEvent( Long eventId);
}
