package com.codespring.ch02.domain;

public class SampleDTO {
    private String name;
    private int age;

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public int getAge() {
        System.out.println("getAge");
        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge");
        this.age = age;
    }

    @Override
    public String toString() {
        return "SampleDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
