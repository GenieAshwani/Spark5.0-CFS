package com.cfs.RedisProject.entity;

import java.io.Serializable;


public class Student implements Serializable {

    private static final long serializableUID=1L;

    private Long id;

    private String name;

    private String email;

    public Student(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Student()
    {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
