package com.nmm.dubbo.service;

import org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;


@Component
public class ReferenceBeanCreator extends ReferenceAnnotationBeanPostProcessor {

    public ReferenceBeanCreator() {
        System.out.println(1111);
    }

    public Object createReference(Class injectClass, Method method, Map<String,Object> map) throws Exception {
        AnnotationAttributes annotationAttributes = new AnnotationAttributes(map);
        return doGetInjectedBean(annotationAttributes,null,null,injectClass,new ReferenceBeanInjectedElement(method,null));
    }


    private class ReferenceBeanInjectedElement extends InjectionMetadata.InjectedElement {

        protected ReferenceBeanInjectedElement(Member member, PropertyDescriptor pd) {
            super(member, pd);
        }
    }
}
