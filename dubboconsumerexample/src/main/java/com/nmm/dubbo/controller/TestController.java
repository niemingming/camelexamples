package com.nmm.dubbo.controller;

import com.nmm.dubbo.service.HelloService;
import com.nmm.dubbo.service.ReferenceBeanCreator;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController implements InitializingBean {

//    @DubboReference(version = "1.0.0")
    private HelloService helloService;
    @Autowired
    private ReferenceBeanCreator beanCreator;

    @GetMapping("/api/v1/hello")
    public String sayHello(String name) {
        return helloService.sayHello(name);
    }

    @GetMapping("/api/v1/dyna")
    public String dynamic(String name) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("version","1.0.0");
//        map.put("url","dubbo://127.0.0.1:11111");
        Method method = HelloService.class.getDeclaredMethod("sayHello",String.class);

        helloService = (HelloService) beanCreator.createReference(HelloService.class,method,map);
        return helloService.sayHello(name);
    }

    public void afterPropertiesSet() throws Exception {
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("version","1.0.0");
//        map.put("url","dubbo://127.0.0.1:11111");
//        Method method = HelloService.class.getDeclaredMethod("sayHello",String.class);
//
//        helloService = (HelloService) beanCreator.createReference(HelloService.class,method,map);
    }
}
