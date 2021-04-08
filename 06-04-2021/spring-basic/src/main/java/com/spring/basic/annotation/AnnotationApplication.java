package com.spring.basic.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AnnotationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnnotationApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Company company = context.getBean("perusahaan", Company.class);
        company.getAddress().setStreet("Jl. Batok");
        System.out.println(company.getAddress().getStreet()); //Jl. Batok

        //OR

        Address address = context.getBean("getAddress",Config.getAddress().getClass());
        System.out.println(address.getStreet()); //Jl. Batok

        address.setStreet("Jl. Gede");
        System.out.println(address.getStreet());


        Company abc = context.getBean("getCompany",Config.getCompany().getClass());
        System.out.println(abc.getAddress().getStreet()); //High Street => from config class

        //Get Other Bean
        String hello = (String)context.getBean("hello",Config.helloWorld());
        System.out.println(hello);

        String helloWorld = (String)context.getBean("hello"); //return based on bean initialize
        System.out.println(helloWorld);
    }
}
