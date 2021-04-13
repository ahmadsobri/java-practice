package com.example.hitapi;

import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = -4009390038255794274L;
    private int id;
    private String first_name;
    private String last_name;
    private String email;

    public Employee(int id, String first_name, String last_name, String email){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
