package com.java.oop.inheritance;

public class MyCalculation extends Calculation implements PrintCtl {
    public MyCalculation(int z){
        super(z);
    }

    public void multiplication(int x, int y) {
        z = x * y;
        System.out.println("The product of the given numbers:" + z);
    }

    @Override //override the same method from parent
    public void display(){
        System.out.println("this is message from sub class");
    }

    @Override //override from interface
    public void show() {
        System.out.println("show method from interface");
    }

    public void getDisplayFromSuperClass(){
        super.display();
    }

    public static void main(String args[]) {
        int a = 20, b = 10;
        MyCalculation demo = new MyCalculation(100); //invoking super class
        demo.addition(a, b);
        demo.Subtraction(a, b);
        demo.multiplication(a, b); //from sub class

        demo.display();
        demo.getDisplayFromSuperClass();
        demo.show();
        System.out.println("invoking super class variable = "+demo.z);
    }

}
