package com.java.oop.inheritance;

public class Calculation {
    int z;
    protected int n;

    public Calculation(){} // default constructor

    public Calculation(int n){ // with param
        n = n;
    }

    public void addition(int x, int y) {
        z = x + y;
        System.out.println("The sum of the given numbers:"+z);
    }

    public void Subtraction(int x, int y) {
        z = x - y;
        System.out.println("The difference between the given numbers:"+z);
    }

    public void display(){
        System.out.println("this is message from super class");
    }
}
