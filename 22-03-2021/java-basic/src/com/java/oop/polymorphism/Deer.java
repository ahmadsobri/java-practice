package com.java.oop.polymorphism;

interface Vegetarian{
    void eat();
}
class Animal{
    void walk(){
        System.out.println("animal walk");
    }
}
public class Deer extends Animal implements Vegetarian{
    void running(){
        System.out.println("deer running");
    }
    public static void main(String[] args) {

//        A Deer IS-A Animal
//        A Deer IS-A Vegetarian
//        A Deer IS-A Deer
//        A Deer IS-A Object

        Deer d = new Deer();
        Animal a = d;
        Vegetarian v = d;
        Object o = d;

        d.running();
        a.walk();
        v.eat();
        System.out.println("o instanceof Deer "+(o instanceof Deer));
    }

    @Override
    public void eat() {
        System.out.println("eat vegetarian");
    }
}