package com.nmm.dubbo.controller;

//import com.nmm.dubbo.service.HelloService;

import com.nmm.dubbo.service.ReferenceBeanCreator;
import org.apache.dubbo.registry.integration.RegistryDirectory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements InitializingBean {

//    @DubboReference(version = "1.0.0")
//    private HelloService helloService;
    @Autowired
    private ReferenceBeanCreator beanCreator;
//    @Autowired
    private RegistryDirectory registryDirectory;

    @GetMapping("/api/v1/hello")
    public String sayHello(String name) {
//        return helloService.sayHello(name);
        return null;
    }

    @GetMapping("/api/v1/dyna")
    public String dynamic(String name) throws Exception {
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("version","1.0.0");
//        map.put("registry",new String[]{"testconsumer"}); //指定registryId，也就是beanid在spring中的。
//        Method method = HelloService.class.getDeclaredMethod("sayHello",String.class);
//
//        helloService = (HelloService) beanCreator.createReference(HelloService.class,method,map);
//        return helloService.sayHello(name);
        return null;
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
