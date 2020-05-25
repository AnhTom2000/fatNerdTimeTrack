package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Tag;

import java.util.List;

/**
 * @author Weleness
 * @date 2020/05/04
 * @description TODO  事件标签中间表服务
 */
public interface Event_TagService {

    ResultDTO addInMiddle(Long eventId, List<Tag> tagList);

    ResultDTO deleteInMiddle(Long tagId);

    void deleteTagInMiddle(Long eventId);

     void deleteOneTag(Long eventId,Long tagId);
}
