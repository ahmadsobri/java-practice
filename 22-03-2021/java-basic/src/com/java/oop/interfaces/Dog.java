package com.java.oop.interfaces;

public class Dog implements Animal, Mammal{

    @Override // from Animal
    public void eat() {
        System.out.println("Dog is eats from Animal");
    }

    @Override // from Animal
    public void travel() {
        System.out.println("Dog is travels from Animal");
    }

    @Override // from Mammal
    public void birth() {
        System.out.println("Dog is birth from Mammal");
    }

    @Override // from Anatomy parent of Mammal
    public void hasLobe() {
        System.out.println("Dog has lobe");
    }

    public int noOfLegs() {
        return 0;
    }

    public static void main(String args[]) {
        Dog d = new Dog();
        d.eat();
        d.travel();
        d.birth();
        d.hasLobe();
    }
}
