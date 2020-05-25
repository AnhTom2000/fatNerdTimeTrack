package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.annotation.Action;
import com.github.anhTom2000.dto.AnniversaryDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Anniversary;
import com.github.anhTom2000.service.AnniversaryService;
import com.github.anhTom2000.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

import static com.github.anhTom2000.config.redis.RestTemplateConfig.COOKIE_SEESION_KEY;

/**
 * @Description : TODO   纪念日控制器
 * @Author : Weleness
 * @Date : 2020/05/11
 */
@RequestMapping("/api/anniversary")
@RestController
public class AnniversaryController {

    @Qualifier("anniversaryService")
    @Autowired
    private AnniversaryService anniversaryService;

    @Autowired
    private CookieService cookieService;

    @Action("findAllAnniversary")
    @RequestMapping("/findAllAnniversary")
    public List<AnniversaryDTO> getAllAnniversary(HttpServletRequest request) {

        List<AnniversaryDTO> anniversaryDTOList = null;
        anniversaryDTOList = anniversaryService.findAllAnniversary((Long) request.getSession().getAttribute(cookieService.getCookie(COOKIE_SEESION_KEY,request).getValue()));
        return anniversaryDTOList;
    }

    @Action("insertAnniversary")
    @RequestMapping("/insertAnniversary")
    public AnniversaryDTO insertAnniversary(@Validated @RequestBody  Anniversary anniversary, HttpServletRequest request) {
       Cookie cookie = cookieService.getCookie(COOKIE_SEESION_KEY,request);
       Long userId = (Long) request.getSession().getAttribute(cookie.getValue());
        return anniversaryService.insertAnniversary(anniversary, userId);
    }

    @Action("updateAnniversary")
    @RequestMapping("/updateAnniversary")
    public ResultDTO updateAnniversary(@RequestBody @Validated Anniversary anniversary) {
        return anniversaryService.updateAnniversary(anniversary);
    }

    @Action("deleteAnniversary")
    @RequestMapping("/deleteAnniversary")
    public ResultDTO deleteAnniversary(@RequestParam("anniversaryId") Long anniversaryId) {
        return anniversaryService.deleteAnniversary(anniversaryId);
    }
}
