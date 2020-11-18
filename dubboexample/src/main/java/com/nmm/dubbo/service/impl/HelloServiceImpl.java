package com.nmm.dubbo.service.impl;

import com.nmm.dubbo.entity.Person;
import com.nmm.dubbo.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@EnableDubbo
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "hello " + name;
    }

    public Person getInfo(String name) {
        Person person = new Person();
        person.setName(name);
        person.setAge(22);
        person.setBirthday(new Date());
        return person;
    }

    public void addPerson(String name, Integer age, Date birthday) {
        log.info("name:{},age:{},birthday:{}",name,age,birthday);
    }

    public List<Person> list() {
        List<Person> persons = new ArrayList<Person>();
        persons.add(getInfo("张三"));
        persons.add(getInfo("李四"));
        return persons;
    }

    public List<Person> listByNames(List<String> names) {
        return names.stream().map(this::getInfo).collect(Collectors.toList());
    }

    @Override
    public List<Person> remove(List<Person> persons) {
        return persons;
    }

    @Override
    public List<Person> map(Map<String, Person> persons) {
        return persons.values().stream().collect(Collectors.toList());
    }

    @Override
    public void postPerson(Person person) {
        log.info("接收到人员信息：{}，{}，{}",person.getName(),person.getAge(),person.getBirthday() );
    }
}
