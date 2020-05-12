package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dao.Event_TagMapper;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Tag;
import com.github.anhTom2000.service.Event_TagService;
import com.github.anhTom2000.utils.Generator.IdGenerator;
import com.github.anhTom2000.utils.Generator.impl.SnowflakeIdGenerator;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description : TODO   具体的事件标签中间表服务
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@Service("eventTagService")
public class Event_TagServiceImpl implements Event_TagService {



    @Qualifier("eventTagMapper")
    @Autowired
    private Event_TagMapper event_tagMapper;

    @Override
    public ResultDTO addInMiddle(Long eventId, List<Tag> tagList) {
        return event_tagMapper.insertInMiddle(eventId, tagList) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(), "插入成功", true) : new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(), "插入失败", false);
    }

    @Override
    public ResultDTO deleteInMiddle(Long tagId) {
       event_tagMapper.deleteEventTag(tagId);
        return new ResultDTO(Httpcode.OK_CODE.getCode(),"删除成功",true);
    }
}
