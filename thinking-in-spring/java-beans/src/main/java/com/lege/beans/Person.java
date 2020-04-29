package com.lege.beans;

/**
 * @Author 了个
 * @date 2020/4/29 9:55
 */
public class Person {
    private String name = "defaultName";
    private Integer age = 18;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
