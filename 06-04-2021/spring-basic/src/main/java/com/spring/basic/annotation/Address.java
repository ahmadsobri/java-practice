package com.spring.basic.annotation;

public class Address {
    private String street;
    private int number;

    public Address(String street, int number) {
        this.setStreet(street);
        this.setNumber(number);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
