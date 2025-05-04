package com.alcity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class GFG {
    public static void main(String[] args)
    {

        // Creating a list of Integers
        Collection<Integer> list = Arrays.asList(5, -10, 7, -18, 23);

        System.out.println("The sorted stream according "
                + "to provided Comparator is : ");

        // Displaying the list of Strings in
        // reverse order after sorting
        list.stream().sorted(Comparator.naturalOrder()).
                forEach(System.out::println);
    }
}
