package com.spring.accessmysqldatabase.entities;

import javax.persistence.*;

@Entity                                                 // need for JPA
@Table(name = "author")                                 // need for JPA
public class Author {
    @Id                                                 // need for JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // need for JPA
    private int id;
    private String full_name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
