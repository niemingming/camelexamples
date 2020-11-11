package com.nmm.dubbo.controller;

import com.alibaba.fastjson.JSONObject;
import com.nmm.dubbo.complier.JdkCompiler;
import com.nmm.dubbo.entity.ConsumerInfo;
import com.nmm.dubbo.entity.ConsumerProxy;
import com.nmm.dubbo.entity.Parameter;
import com.nmm.dubbo.reference.ReferenceInvoker;
import com.nmm.dubbo.service.ReferenceBeanCreator;
import com.nmm.dubbo.service.RegistryConfigContainer;
import com.nmm.dubbo.util.SourceCreator;
import org.apache.dubbo.rpc.proxy.InvokerInvocationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class DubboConvertorController {
    @Autowired
    private ReferenceBeanCreator beanCreator;

    private Map<String, ConsumerProxy> clazzs = new ConcurrentHashMap<String,ConsumerProxy>();
    @Autowired
    private RegistryConfigContainer registryConfigContainer;

    /**
     * 添加接口声明
     * @return
     */
    @PostMapping("/api/v1/addDubbo")
    public String addInterface(@RequestBody ConsumerInfo consumerInfo) throws Throwable {
        String className = consumerInfo.getClassName();
        String simpleName = className.indexOf(".") > 0 ? className.substring(className.lastIndexOf(".") + 1) : className;
        List<String> argTypes = consumerInfo.getArgs().stream().map(Parameter::getType).collect(Collectors.toList());
        String code = SourceCreator.sourceCode(className,consumerInfo.getMethodName(),argTypes.toArray(new String[consumerInfo.getArgs().size()]));



//        System.out.println(new JdkCompiler().doCompile(className,code));
        JdkCompiler jdkCompiler = new JdkCompiler();
        //编译成功
        Class clazz = jdkCompiler.doCompile(className,code);

        Method method = clazz.getDeclaredMethods()[0];

        ReferenceInvoker referenceInvoker = new ReferenceInvoker(clazz,"1.0.0","zookeeper://localhost:2181");
        InvokerInvocationHandler handler = referenceInvoker.getInvokerHandler();
        Object res = handler.invoke(null,method,new Object[]{"world"});
        System.out.println(res);
        //获取consumer
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        Thread.currentThread().setContextClassLoader(jdkCompiler.getClassLoader());
//        Object consumer = beanCreator.createReference(clazz,method,consumerInfo.toAttributes());
//        Thread.currentThread().setContextClassLoader(loader);
//
//        ConsumerProxy proxy = new ConsumerProxy();
//        proxy.setConsumer(consumer);
//        proxy.setMethod(method);
//        proxy.setParameters(consumerInfo.getArgs());
//        clazzs.put(className,proxy);
//        String registry = registryConfigContainer.addRegistry(consumerInfo.getRegistryUrl(),simpleName + "registry");
        return  "";
    }

    /**
     * 调用指定的dubbo
     * @param className
     * @param map
     * @return
     */
    @PostMapping("/api/v1/invoke")
    public String invoke(String className,@RequestBody JSONObject map) throws InvocationTargetException, IllegalAccessException {
        return JSONObject.toJSONString(clazzs.get(className).invoke(map));
    }

}
