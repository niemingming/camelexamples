package com.nmm.dubbo.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 消费者代理类
 */
@Data
public class ConsumerProxy {
    private Object consumer;
    private Method method;
    private List<Parameter> parameters;

    /**
     * 调用consumer
     * @param args
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object invoke(JSONObject args) throws InvocationTargetException, IllegalAccessException {
        Class[] types = method.getParameterTypes();
        int i = 0;
        String stringclass = String.class.getName();
        String intclass = int.class.getName();
        String integerclass = Integer.class.getName();
        String doubleclass = double.class.getName();
        String Doubleclass = Double.class.getName();
        String booleanclass = boolean.class.getName();
        String Booleanclass = Boolean.class.getName();

        Object [] values = new Object[parameters.size()];
        for (Parameter parameter : parameters) {
            Class type = types[i++];
            String typename = type.getName();
            Object value = null;
            if (typename.equals(stringclass)) {
                value = args.getString(parameter.getName());
            }else if (typename.equals(intclass) || typename.equals(integerclass)) {
                value = args.getInteger(parameter.getName());
            }else if (typename.equals(doubleclass) || typename.equals(Doubleclass)) {
                value = args.getDouble(parameter.getName());
            }else if (typename.equals(booleanclass)||typename.equals(Booleanclass)) {
                value = args.getBoolean(parameter.getName());
            }else {
                //自定义对象了，暂时不考虑
                value = args.get(parameter.getName()).toString();
            }
            values[i-1] = value;
        }
        return method.invoke(consumer,values);
    }
}
