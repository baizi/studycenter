package com.study.model;

/**
 * Created by zhangshuwen
 * Date is 2016/8/7.
 * Time is 15:22
 */
public class Person {

    private String name;
    private int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void test() throws Exception {
        Thread.sleep(1000);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
