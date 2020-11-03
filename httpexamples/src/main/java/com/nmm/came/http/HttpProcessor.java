package com.nmm.came.http;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpMessage;

public class HttpProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        HttpMessage message = (HttpMessage) exchange.getIn();
        System.out.println("获取消息信息：" + message);
        System.out.println("重定向了！");
        message.getResponse().sendRedirect("https://www.baidu.com");
    }
}
