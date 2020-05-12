package com.github.anhTom2000.config.redis;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 11:21  21/01/2020
 * Description:
 *
 * @author Weleness
 */
@SpringBootConfiguration
public class RestTemplateConfig {

    /**
     * @Method Description:
     * 配置restTemplate  restTemplate是Springboot集成的一个httpClient请求工具 ， 用请求一些网络资源
     * @Author weleness
     * @Return
     */
    @Bean("restTemplates")
    public RestTemplate getRestTemplate() {
        // 创建请求实例工厂
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置最大的读取时间
        requestFactory.setReadTimeout(120000);
        // 设置解析器集合
        List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
        // 解析二进制数据
        messageConverters.add(new ByteArrayHttpMessageConverter());
        //解析字符串,字符集是utf-8
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 这三个没看懂....  以后技术精进的时候回来看看,先mark
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter<Source>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        // 解析json字符串
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        //创建restTemplate示例,将配置好的解析器集合放进去
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        // 设置请求工厂
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    public static final String COOKIE_SEESION_KEY = "fat-user";

    public static final int TimeOut = 432000;

}
