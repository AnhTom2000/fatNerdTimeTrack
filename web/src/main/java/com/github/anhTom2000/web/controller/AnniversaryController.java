package com.github.anhTom2000.web.controller;

import com.github.anhTom2000.dto.AnniversaryDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Anniversary;
import com.github.anhTom2000.service.AnniversaryService;
import com.github.anhTom2000.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/findAllAnniversary")
    public List<AnniversaryDTO> getAllAnniversary(HttpServletRequest request) {
        Cookie cookie;
        Long userId;
        List<AnniversaryDTO> anniversaryDTOList= null;
        if ((cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request)) != null) {
            anniversaryDTOList = anniversaryService.findAllAnniversary((Long) request.getSession().getAttribute(cookie.getValue()));
        }
        return anniversaryDTOList;
    }

    @RequestMapping("/insertAnniversary")
    public AnniversaryDTO insertAnniversary(@RequestBody Anniversary anniversary,HttpServletRequest request){
        Cookie cookie;
        System.out.println(anniversary);
      AnniversaryDTO resultDTO = null;
        if ((cookie = cookieService.getCookie(COOKIE_SEESION_KEY, request)) != null) {
            resultDTO = anniversaryService.insertAnniversary(anniversary,(Long) request.getSession().getAttribute(cookie.getValue()));
        }
        return resultDTO;
    }

    @RequestMapping("/updateAnniversary")
    public ResultDTO updateAnniversary(@RequestBody Anniversary anniversary){
        return anniversaryService.updateAnniversary(anniversary);
    }


    @RequestMapping("/deleteAnniversary")
    public ResultDTO deleteAnniversary(@RequestParam("anniversaryId") Long anniversaryId){
        return anniversaryService.deleteAnniversary(anniversaryId);
    }
}
