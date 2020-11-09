package com.nmm.dubbo.service.impl;

import com.nmm.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;

@EnableDubbo
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "hello " + name;
    }
}
