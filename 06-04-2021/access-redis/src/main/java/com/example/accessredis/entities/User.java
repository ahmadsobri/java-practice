package com.example.accessredis.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long followers;

    public User() {
    }

    public User(String name, long followers) {
        this.setName(name);
        this.setFollowers(followers);
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', followers=%d}", getId(), getName(), getFollowers());
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

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }
}