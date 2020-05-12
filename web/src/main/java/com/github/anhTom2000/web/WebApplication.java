package com.github.anhTom2000.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;



@ServletComponentScan
@MapperScan(basePackages = {"com.github.anhTom2000.dao"},basePackageClasses = Repository.class)
@SpringBootApplication(scanBasePackages = {"com.github.anhTom2000"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
