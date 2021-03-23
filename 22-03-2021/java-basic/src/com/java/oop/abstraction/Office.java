package com.java.oop.abstraction;

public class Office {
    public static void main(String[] args) {
        //error because Employee is abstract cannot be instantiated
        //Employee e = new Employee("George W.", "Houston, TX", 43);

        //alternative with class that extends the Employee
        Salary s = new Salary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
        Employee e = new Salary("John Adams", "Boston, MA", 2, 2400.00);

        System.out.println("\n Call mailCheck using Salary reference --");
        s.mailCheck();

        System.out.println("\n Call mailCheck using Employee reference--");
        e.mailCheck();
    }
}
