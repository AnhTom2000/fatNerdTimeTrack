package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.dto.TagDTO;
import com.github.anhTom2000.entity.Tag;
import com.github.anhTom2000.service.CookieService;
import com.github.anhTom2000.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.COOKIE_SEESION_KEY;
/**
 * @Description : TODO
 * @Author : Weleness
 * @Date : 2020/05/05
 */
@RequestMapping("/api/tag")
@RestController
public class TagController {

    @Qualifier("tagService")
    @Autowired
    private TagService tagService;

    @Qualifier("cookieService")
    @Autowired
    private CookieService cookieService;

    @PostMapping("/addTag")
    public TagDTO addTag(@RequestBody Tag tag,HttpServletRequest request){
        System.out.println(tag);
        Cookie cookie = null;
        Long userId = null;
        TagDTO tagDTO =null;
        if((cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request))!=null){
            if((userId = (Long) request.getSession().getAttribute(cookie.getValue()))!=null){
                tagDTO = tagService.addTag(tag,userId);
            }
        }
        return tagDTO;
    }

    @RequestMapping("/findAll")
    public List<TagDTO> findAllTag(HttpServletRequest request){
        Cookie cookie = null;
        Long userId = null;
        List<TagDTO> tag = null;
        if((cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request))!=null){
            if((userId = (Long) request.getSession().getAttribute(cookie.getValue()))!=null){
                 tag= tagService.findAllTag(userId);
            }
        }
        return tag;
    }

    @RequestMapping("/updateTagColor")
    public ResultDTO updateTagColor(@RequestParam("color") String color,@RequestParam("tagId")Long tagId){
        return tagService.updateTagColor(color, tagId);
    }

    @RequestMapping("/updateTagName")
    public ResultDTO updateTagName(@RequestParam("tagName") String tagName,@RequestParam("tagId")Long tagId){
        return tagService.updateTagName(tagName, tagId);
    }

}
