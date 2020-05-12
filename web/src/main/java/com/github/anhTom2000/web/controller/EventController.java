package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.dto.EventDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Event;
import com.github.anhTom2000.service.CookieService;
import com.github.anhTom2000.service.EventService;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.COOKIE_SEESION_KEY;

/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@RequestMapping("/api/event")
@RestController
public class EventController {

    @Qualifier("eventService")
    @Autowired
    private EventService eventService;

    @Qualifier("cookieService")
    @Autowired
    private CookieService cookieService;

    @PostMapping("/addEvent")
    public EventDTO addEvent(@RequestBody Event event, HttpServletRequest request) {
        EventDTO result = null;
        Long userId = null;
        Cookie cookie = null;
        if ((cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request)) != null) {
            if ((userId = (Long) request.getSession().getAttribute(cookie.getValue())) != null) {
                event.setUserId(userId);
                result = eventService.addEvent(event);
            }
        }
        return result;
    }

    @RequestMapping("/getEvent")
    public List<EventDTO> getEvent(HttpServletRequest request) {
        Long userId = null;
        Cookie cookie = null;
        List<EventDTO> event = null;
        if ((cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request)) != null) {
            if ((userId = (Long) request.getSession().getAttribute(cookie.getValue())) != null) {
                event = eventService.getEvent(userId);
            }
        }
        System.out.println(event);
        return event;
    }

    @RequestMapping("/updateEventDescription")
    public ResultDTO updateEventDescription(@RequestParam("eventDescription") String description, @RequestParam("eventId") Long eventId) {
        return eventService.updateDescription(description, eventId);
    }

    @RequestMapping("/updateEventTitle")
    public ResultDTO updateEventTitle(@RequestParam("eventTitle") String eventTitle, @RequestParam("eventId") Long eventId) {
        return eventService.updateEventTitle(eventTitle, eventId);
    }

    @RequestMapping("/updateEventFinished")
    public ResultDTO updateEventFinished(@RequestParam("eventId") Long eventId) {
        return eventService.updateEventFinished(eventId);
    }

    @RequestMapping("/updateEventDate")
    public ResultDTO updateEventDate(@RequestParam("date")LocalDateTime date, @RequestParam("eventId") Long eventId) {
        return eventService.updateEventDate(date, eventId);
    }

    @RequestMapping("/updateEventPriority")
    public ResultDTO updateEventPriority(@RequestParam("priorityId") Integer priorityId, @RequestParam("eventId") Long eventId) {
        return eventService.updateEventPriority(priorityId, eventId);
    }

    @RequestMapping("deleteEvent")
    public ResultDTO deleteEvent(@RequestParam("eventId") Long eventId){
        return eventService.deleteEvent(eventId);
    }


}
