package com.nmm.dubbo.controller;

import com.nmm.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @DubboReference(version = "1.0.0",url = "dubbo://127.0.0.1:12345")
    private HelloService helloService;

    @GetMapping("/api/v1/hello")
    public String sayHello(String name) {
        return helloService.sayHello(name);
    }

}
