package com.java.basic.string;

public class MyString {
    public static void main(String[] args) {
        float floatVar = 2.44f;
        int intVar = 43;
        String stringVar = "hello";
        char[] helloArray = { 'h', 'e', 'l', 'l', 'o', '.' };

        String helloString = new String(helloArray);
        int lengthOfString = helloString.length();
        System.out.println( helloString );
        System.out.println( "length of "+helloString+" is "+lengthOfString );

        System.out.printf("The value of the float variable is " +
                "%f, while the value of the integer " +
                "variable is %d, and the string " +
                "is %s", floatVar, intVar, stringVar);

        System.out.println("\n");

        //or you can write
        String fs = String.format("The value of the float variable is " +
                "%f, while the value of the integer " +
                "variable is %d, and the string " +
                "is %s", floatVar, intVar, stringVar);
        System.out.println(fs);

    }
}
