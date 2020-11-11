package com.nmm.dubbo.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ConsumerInfo {

    private String className;
    private String version;
    private int timeout = 10000;//默认10秒
    private String  methodName;
    private boolean generic;//是否采用通用调用方式,默认是false
    private List<Parameter> args; //请求参数
    private String registryUrl;

    public Map<String,Object> toAttributes(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("version",version);
        map.put("timeout",timeout);
        map.put("generic",generic);
        return map;
    }

}
