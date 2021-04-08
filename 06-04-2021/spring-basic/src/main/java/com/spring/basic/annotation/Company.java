package com.spring.basic.annotation;

import org.springframework.stereotype.Component;

@Component("perusahaan")
public class Company {
    private Address address;

    public Company(Address address) {
        this.setAddress(address);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
