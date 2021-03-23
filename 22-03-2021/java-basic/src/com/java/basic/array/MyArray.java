package com.java.basic.array;

public class MyArray {

    public static int[] reverse(int[] list) {
        int[] result = new int[list.length];

        for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
            result[j] = list[i];
        }
        return result;
    }

    public static void main(String[] args) {
        double[] myList = {1.9, 2.9, 3.4, 3.5};
        int[] myIntList = {1, 2, 3, 4};
        int[] results = reverse(myIntList);

        // Print all the array elements
        for (int i = 0; i < myList.length; i++) {
            System.out.println(myList[i] + " ");
        }

        // foreach Print all the array elements
        for (double element: myList) {
            System.out.println(element);
        }

        // Summing all elements
        double total = 0;
        for (int i = 0; i < myList.length; i++) {
            total += myList[i];
        }
        System.out.println("Total is " + total);

        // Finding the largest element
        double max = myList[0];
        for (int i = 1; i < myList.length; i++) {
            if (myList[i] > max) max = myList[i];
        }
        System.out.println("Max is " + max);

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i] + " ");
        }

    }
}
