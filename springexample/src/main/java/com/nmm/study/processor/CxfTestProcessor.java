package com.nmm.study.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpMessage;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component
public class CxfTestProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        //做消息转换
        HttpMessage message = (HttpMessage) exchange.getIn();
        String path = message.getRequest().getRequestURI();
        path = path.substring(path.lastIndexOf("/") + 1);
        //这里需要动态生成
        StringBuilder body = new StringBuilder("<ser:" + path + " xmlns:ser=\"http://webservice.study.nmm.com/\">");
        //这里暂时只做参数处理
        Enumeration<String> enumeration = message.getRequest().getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            body.append("<").append(name).append(">");
            body.append(message.getRequest().getParameter(name));
            body.append("</").append(name).append(">");
        }
        body.append("</ser:" + path + ">");
        System.out.println(body);
        exchange.getIn().setBody(body.toString());
    }
}
