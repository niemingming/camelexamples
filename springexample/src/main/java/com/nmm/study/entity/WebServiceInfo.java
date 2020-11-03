package com.nmm.study.entity;

import lombok.Data;

@Data
public class WebServiceInfo {
    /**webservice地址*/
    private String serviceAddress = "http://localhost:9000/hello/webservice";
    /**路由监听地址*/
    private String listenerAddress = "jetty:http://0.0.0.0:8888";
    /**路由路径*/
    private String path;
    /**wsdl路径*/
    private String wsdlURL = serviceAddress + "?wsdl";
    /**数据传输格式*/
    private String dataFormat = "PAYLOAD";
    /**namespace*/
    private String targetNameSpace="http://webservice.study.nmm.com/";
    /**请求方法，也就是操作路径*/
    private String operationName;

}
