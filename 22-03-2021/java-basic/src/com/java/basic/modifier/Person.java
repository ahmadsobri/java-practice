package com.java.basic.modifier;

public class Person {
    private boolean isHasBigSalary;
    static final double age = 9.5;
    protected static final int BODY_HEIGHT = 42;
    protected static int Weight = 55;

    public static void main(String[] args) {
        //System.out.println(isHasBigSalary); //cannot access because is not static
        System.out.println("age "+age);
        System.out.println("height "+BODY_HEIGHT);
        //age = 20.5; //cannot assign value because is final

        System.out.println("weight "+Weight);

        Weight = 70; // assign change value
        System.out.println("weight "+Weight);
    }
}
