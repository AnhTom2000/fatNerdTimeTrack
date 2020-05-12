package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.TagDTO;
import com.github.anhTom2000.entity.Tag;

import java.util.List;

/**
 * @Description : TODO  标签服务
 * @Author : Weleness
 * @Date : 2020/05/04
 */
public interface TagService {

    TagDTO addTag(Tag tag, Long userId);

    List<TagDTO> findAllTag(Long userId);

    ResultDTO updateTagName(String tagName, Long tagId);

    ResultDTO updateTagColor(String tagColor, Long tagId);

    ResultDTO deleteTag(Long tagId);
}
