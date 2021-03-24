package com.java.hibernate.entities;

import javax.persistence.*;

@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //If you want to use a table's identity column
    //Note : GenerationType.AUTO or GenerationType.SEQUENCE need table hibernate_sequence in your db
    private int id;

    @Column(name = "full_name")
    private String fullname;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
