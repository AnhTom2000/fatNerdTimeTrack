package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dao.EventMapper;
import com.github.anhTom2000.dto.EventDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Event;
import com.github.anhTom2000.entity.Tag;
import com.github.anhTom2000.service.EventService;
import com.github.anhTom2000.service.Event_TagService;
import com.github.anhTom2000.service.UserService;
import com.github.anhTom2000.utils.BeanConvert.BeanConvertUtil;
import com.github.anhTom2000.utils.Generator.IdGenerator;
import com.github.anhTom2000.utils.Generator.impl.SnowflakeIdGenerator;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : TODO  具体的事件服务实现
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@Service("eventService")
public class EventServiceImpl implements EventService {
    private final IdGenerator idGenerator = SnowflakeIdGenerator.getInstance();

    @Qualifier("eventMapper")
    @Autowired
    private EventMapper eventMapper;

    @Qualifier("eventTagService")
    @Autowired
    private Event_TagService event_tagService;


    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public EventDTO addEvent(Event event) {
        event.setEventId(idGenerator.generateId());
        if (event.getTagList().size() > 0) event_tagService.addInMiddle(event.getEventId(), event.getTagList());
        return eventMapper.addEvent(event) > 0 ? BeanConvertUtil.create(event, EventDTO.class) : null;
    }

    @Override
    public List<EventDTO> getEvent(Long userId) {
        List<Event> events = eventMapper.findAllEventByUserId(userId);
        return events.size() < 1 ? new ArrayList<EventDTO>() : BeanConvertUtil.convertList(events, EventDTO.class);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO updateEventTitle(String eventTitle, Long eventId) {
        return eventMapper.updateEventTitle(eventId, eventTitle) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(), "修改成功", true) : new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "失败", false);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO updateDescription(String description, Long eventId) {
        return eventMapper.updateDescription(description, eventId) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(), "修改成功", true) : new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "失败", false);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO updateEventFinished(Long eventId) {
        return eventMapper.updateEventFinished(eventId) > 0 && eventMapper.updateEventEndTime(LocalDateTime.now(Clock.systemDefaultZone()), eventId) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(), "修改成功", true) : new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "失败", false);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO updateEventDate(LocalDateTime date, Long eventId) {
        return eventMapper.updateEventDate(date, eventId) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(), "修改成功", true) : new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "失败", false);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO updateEventPriority(Integer priorityId, Long eventId) {
        return eventMapper.updateEventPriority(priorityId, eventId) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(), "修改成功", true) : new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "失败", false);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO deleteEvent(Long eventId) {
        eventMapper.deleteEvent(eventId);
        return new ResultDTO(Httpcode.OK_CODE.getCode(), "修改成功", true);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO deleteEventTag(Long eventId, Long tagId) {
        event_tagService.deleteOneTag(eventId, tagId);
        return new ResultDTO(Httpcode.OK_CODE.getCode(),null,true);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public ResultDTO updateEventTag(List<Tag> tagList, Long eventId) {
        // 先将原来的标签删除
        event_tagService.deleteTagInMiddle(eventId);
        // 添加新的标签
        if(tagList!=null)
        event_tagService.addInMiddle(eventId,tagList);

        return new ResultDTO(Httpcode.OK_CODE.getCode(),null,true);
    }
}
