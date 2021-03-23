package com.java.oop.overriding;

public class Zoo {
    public static void main(String[] args) {
        Animal a = new Animal();   // Animal reference and object
        Animal b = new Dog();   // Animal reference but Dog object

        a.move();   // runs the method in Animal class
        b.move();   // runs the method in Dog class
        //b.bark(); // error because b is Reference Animal, not have bark
    }
}
