package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.service.UploadService;
import com.github.anhTom2000.service.UserService;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import com.github.anhTom2000.utils.uploadUtils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description : TODO  文件上传服务具体实现
 * @Author : Weleness
 * @Date : 2020/05/12
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {
    
    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Value("${uploadPath_user}")
    private String path;

    @Override
    public ResultDTO uploadUserAvator(Long userId, MultipartFile file, HttpServletRequest request) {

        String upload = UploadUtil.upload(file, path);
        System.out.println(upload);
        userService.updateUserAvator(upload,userId);
        return ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message(upload).status(true).build();
    }
}
