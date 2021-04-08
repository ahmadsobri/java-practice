package com.spring.basic.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Company.class)
public class Config {
    @Bean
    public static Address getAddress() {
        return new Address("High Street", 1000);
    }

    @Bean
    public static Company getCompany() {
        return new Company(getAddress());
    }

    @Bean("hello")
    public static String helloWorld(){
        return "Hello World!";
    }
}