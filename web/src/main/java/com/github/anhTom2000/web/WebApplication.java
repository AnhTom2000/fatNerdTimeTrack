package com.github.anhTom2000.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableRedisHttpSession
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ServletComponentScan
@MapperScan(basePackages = {"com.github.anhTom2000.dao"},basePackageClasses = Repository.class)
@SpringBootApplication(scanBasePackages = {"com.github.anhTom2000"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
