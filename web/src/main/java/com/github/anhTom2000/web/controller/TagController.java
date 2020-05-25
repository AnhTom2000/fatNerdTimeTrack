package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.annotation.Action;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @Action("addTag")
    @PostMapping("/addTag")
    public TagDTO addTag(@RequestBody @Valid Tag tag, HttpServletRequest request, HttpSession session) {
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request);
        Long userId = (Long) session.getAttribute(cookie.getValue());
        return tagService.addTag(tag, userId);
    }

    @Action("findAll")
    @RequestMapping("/findAll")
    public List<TagDTO> findAllTag(HttpServletRequest request,HttpSession session) {
        Long userId = null;
        Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request);
        userId = (Long) session.getAttribute(cookie.getValue());
        return tagService.findAllTag(userId);
    }

    @Action("updateTagColor")
    @RequestMapping("/updateTagColor")
    public ResultDTO updateTagColor(@RequestParam("color") String color, @RequestParam("tagId") Long tagId) {
        return tagService.updateTagColor(color, tagId);
    }

    @Action("updateTagName")
    @RequestMapping("/updateTagName")
    public ResultDTO updateTagName(@RequestParam("tagName") String tagName, @RequestParam("tagId") Long tagId) {
        return tagService.updateTagName(tagName, tagId);
    }

}
