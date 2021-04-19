package com.spring.basic.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // for scan @WebFilter
@SpringBootApplication
public class FilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }
}
