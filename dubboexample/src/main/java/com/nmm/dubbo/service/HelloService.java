package com.nmm.dubbo.service;

import com.nmm.dubbo.entity.Person;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HelloService {

    public String sayHello(String name);

    public Person getInfo(String name);

    public void addPerson(String name , Integer age, Date birthday);

    public List<Person> list();

    public List<Person> listByNames(List<String> names);

    public List<Person> remove(List<Person> persons);

    public List<Person> map(Map<String,Person> persons);

    public void postPerson(Person person);
}
