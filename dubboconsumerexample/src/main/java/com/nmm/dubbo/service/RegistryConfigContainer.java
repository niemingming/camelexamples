package com.nmm.dubbo.service;

import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegistryConfigContainer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 集群配置暂时不处理，稍后统一处理这里做可行性验证
     * @param address
     * @return
     */
    public String addRegistry(String address,String name) {

        RegistryConfig registryConfig = new RegistryConfig(address);
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) ((ConfigurableApplicationContext)applicationContext).getBeanFactory();
        if (factory.getBean(name) != null) {
            return name;
        }
        factory.registerSingleton(name,factory);
        return name;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
