package com.nmm.came.http;

import org.apache.camel.builder.RouteBuilder;

public class HttpRouter extends RouteBuilder {
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8888/hello")
                .process(new HttpProcessor())
                .to("log:httpRouter?showExchangeId=true");
    }
}
