package com.nmm.dubbo.service.impl;

import com.nmm.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "hello " + name;
    }
}
