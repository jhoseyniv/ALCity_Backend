package com.alcity.test;

public class FloatToIntConversion {
    public static void main(String[] args) {
        Float myFloat = 123.75f;
        Integer myInt =  myFloat.intValue(); // Explicitly cast float to int
        Long levelUpSize=10L;
        Long level = (long) (myFloat / levelUpSize);
        Float reminder = (Float) (myFloat % levelUpSize);

        System.out.println("Level is: " + level);
        System.out.println("remainder is : " + reminder);
    }
}
