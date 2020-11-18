package com.nmm.dubbo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Person implements Serializable {

    private String name;
    private int age;
    private Date birthday;

}
