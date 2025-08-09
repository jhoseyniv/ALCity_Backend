package com.alcity.test;

public class FloatToIntConversion {
    public static void main(String[] args) {
        Float myFloat = 12.75f;
        Integer myInt =  myFloat.intValue(); // Explicitly cast float to int

        System.out.println("Original float: " + myFloat);
        System.out.println("Converted int: " + myInt);
    }
}
