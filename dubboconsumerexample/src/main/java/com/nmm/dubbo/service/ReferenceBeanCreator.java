package com.nmm.dubbo.service;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;


@Component
public class ReferenceBeanCreator extends ReferenceAnnotationBeanPostProcessor{

    private ApplicationContext applicationContext;

    public ReferenceBeanCreator() {
    }

    public Object createReference(Class injectClass, Method method, Map<String,Object> map) throws Exception {
        AnnotationAttributes annotationAttributes = new AnnotationAttributes(map);
        return doGetInjectedBean(annotationAttributes,null,null,injectClass,new ReferenceBeanInjectedElement(method,null));
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) ((ConfigurableApplicationContext)applicationContext).getBeanFactory();
        factory.registerSingleton("testconsumer",new RegistryConfig("zookeeper://localhost:2181"));
        System.out.println("注册完成");
    }

    private class ReferenceBeanInjectedElement extends InjectionMetadata.InjectedElement {
        protected ReferenceBeanInjectedElement(Member member, PropertyDescriptor pd) {
            super(member, pd);
        }
    }
}
