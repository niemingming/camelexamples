package com.nmm.study.controller;

import com.nmm.study.entity.WebServiceInfo;
import com.nmm.study.processor.CxfTestProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private CxfTestProcessor processor;

    @GetMapping("/test/sayHello")
    public String sayHello(String name ) {
        System.out.println(camelContext);
        return "hello " + name;
    }

    @PostMapping("/test/routes")
    public String addRoute(@RequestBody final WebServiceInfo webServiceInfo) throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(webServiceInfo.getListenerAddress() + webServiceInfo.getPath())
                        .process(processor)
                        .to("cxf:" + webServiceInfo.getServiceAddress()
                                + "?wsdlURL=" + webServiceInfo.getWsdlURL()
                                + "&dataFormat=" + webServiceInfo.getDataFormat()
                                + (webServiceInfo.getOperationName() == null ? "" : "&defaultOperationName=" + webServiceInfo.getOperationName()))
                        .convertBodyTo(String.class);
            }
        });
        return "success";
    }
}
