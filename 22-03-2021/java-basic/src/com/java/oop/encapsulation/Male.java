package com.java.oop.encapsulation;

public class Male {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("James");
        person.setAge(20);
        person.setIdNum("12343ms");

        System.out.print("Name : " + person.getName() + " Age : " + person.getAge());
    }
}
