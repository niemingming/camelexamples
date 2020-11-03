package com.nmm.came.http;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HttpDemo {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.start();
        camelContext.addRoutes(new HttpRouter());

//        while (true){
//            Thread.sleep(10000);
//        }
    }
}
