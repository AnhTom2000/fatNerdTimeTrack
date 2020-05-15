package com.github.anhTom2000.utils.uploadUtils;

import com.github.anhTom2000.utils.Generator.IdGenerator;
import com.github.anhTom2000.utils.Generator.impl.SnowflakeIdGenerator;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description : TODO  文件上传工具类
 * @Author : Weleness
 * @Date : 2020/05/12
 */

public class UploadUtil {


    private static final IdGenerator ID_GENERATOR = SnowflakeIdGenerator.getInstance();

    public static final String COS_URL = "https://weleness-1300955279.cos.ap-guangzhou.myqcloud.com/cdn/timeTrack/";

    public static String upload(MultipartFile file, String filePath) {
        //生成一个全局唯一的雪花id
        Long snowId = ID_GENERATOR.generateId();

        String filename = file.getOriginalFilename();
        // 获取文件后缀名
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 生成新文件的名字
        String fileName = snowId + "_" + suffix;

        COSCredentials cred = new BasicCOSCredentials(secretid, secretkey);
        //设置时区
        Region region = new Region("ap-guangzhou");
        //设置客户端时区
        ClientConfig clientConfig = new ClientConfig(region);
        //生成客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        //存储桶名称，格式：BucketName-APPID
        String bucket = "weleness-1300955279";

        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File targetFile = new File(fileDir.getAbsolutePath() + File.separator.concat(fileName));

        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //上传对象
        String key = "cdn/timeTrack/".concat(fileName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, targetFile);
        //上传对象结果集获取
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        return COS_URL.concat(fileName);
    }
}
