package com.java.basic.loop;

public class Loop {
    public static void loopFor(){
        int [] numbers = {10, 20, 30, 40, 50};

        for(int x : numbers ) {
            System.out.print( x );
            System.out.print(",");
        }
        System.out.print("\n");
        String [] names = {"James", "Larry", "Tom", "Lacy"};

        for( String name : names ) {
            System.out.print( name );
            System.out.print(",");
        }
    }

    public static void loopWhile(){
        int [] numbers = {10, 20, 30, 40, 50};
        int n = 0;
        while (n<4){
            System.out.println("while value of n "+n);
            n++;
        }
    }

    public static void loopDoWhile(){
        int n = 0;
        do {
            System.out.println("do while value of n "+n);
            n++;
        }
        while (n<5);
    }

    public static void main(String[] args) {
        loopFor();
        loopWhile();
        loopDoWhile();
    }
}
