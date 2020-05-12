package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dao.TagMapper;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.TagDTO;
import com.github.anhTom2000.entity.Tag;
import com.github.anhTom2000.service.TagService;
import com.github.anhTom2000.utils.BeanConvert.BeanConvertUtil;
import com.github.anhTom2000.utils.Generator.IdGenerator;
import com.github.anhTom2000.utils.Generator.impl.SnowflakeIdGenerator;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : TODO  具体的标签服务
 * @Author : Weleness
 * @Date : 2020/05/04
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Qualifier("tagMapper")
    @Autowired
    private TagMapper tagMapper;

    private final IdGenerator idGenerator = SnowflakeIdGenerator.getInstance();

    @Override
    public TagDTO addTag(Tag tag, Long userId) {
        tag.setTagId(idGenerator.generateId());
        return tagMapper.addTag(tag, userId) > 0 ? BeanConvertUtil.convert(tag, new TagDTO()) : null;
    }

    @Override
    public List<TagDTO> findAllTag(Long userId) {
        List<Tag> tag = tagMapper.findAllTag(userId);
        return tag.size() > 0 ? BeanConvertUtil.convertList(tag, TagDTO.class) : new ArrayList<TagDTO>();
    }

    @Override
    public ResultDTO updateTagName(String tagName, Long tagId) {
        return tagMapper.updateTagName(tagName, tagId) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(),"修改成功",true) : new ResultDTO(Httpcode.SERVER_ERROR_CODE.getCode(),"服务器错误",false) ;
    }

    @Override
    public ResultDTO updateTagColor(String tagColor, Long tagId) {
        return tagMapper.updateTagColor(tagColor, tagId) > 0 ? new ResultDTO(Httpcode.OK_CODE.getCode(),"修改成功",true) : new ResultDTO(Httpcode.SERVER_ERROR_CODE.getCode(),"服务器错误",false) ;
    }

    @Override
    public ResultDTO deleteTag(Long tagId) {
        return null;
    }
}
