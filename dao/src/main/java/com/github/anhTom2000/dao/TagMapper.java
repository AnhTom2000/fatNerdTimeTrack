package com.github.anhTom2000.dao;

import com.github.anhTom2000.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Weleness
 * @date 2020/05/04
 * @description TODO
 */
@Repository
public interface TagMapper {

    Integer addTag(@Param("tag")Tag tag,@Param("userId") Long userId);

    List<Tag> findAllTag(@Param("userId")Long userId);

    Integer updateTagName(@Param("tagName") String tagName,@Param("tagId")Long tagId);

    Integer updateTagColor(@Param("tagColor") String tagColor,@Param("tagId")Long tagId);

    Integer deleteTag(@Param("tagId") Long tagId);
}
