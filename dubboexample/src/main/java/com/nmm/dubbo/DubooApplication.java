package com.nmm.dubbo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubooApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubooApplication.class,args);
    }
}
