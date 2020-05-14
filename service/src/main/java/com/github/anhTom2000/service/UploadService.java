package com.github.anhTom2000.service;

import com.github.anhTom2000.dto.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Weleness
 * @date 2020/05/12
 * @description TODO  文件上传服务
 */
public interface UploadService {

    ResultDTO uploadUserAvator(Long userId, MultipartFile file, HttpServletRequest request);
}
