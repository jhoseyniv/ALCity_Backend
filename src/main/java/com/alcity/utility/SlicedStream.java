package com.alcity.utility;

import java.util.*;
import java.util.stream.*;
public class SlicedStream {

    public static <T> Stream<T>
    getSliceOfStream(Stream<T> stream, Long startIndex, Long endIndex)
    {
        return stream.collect(Collectors.collectingAndThen(

                // 1st argument
                // Convert the stream to list
                Collectors.toList(),

                // 2nd argument
                list -> list.stream()
                        // specify the number of elements to skip
                        .skip(startIndex)

                        // specify the no. of elements the stream
                        // that should be limited
                        .limit(endIndex - startIndex + 1)));
    }
    public static void main(String[] args)
    {

        // Create a new List with values 11 - 20
        List<Integer> list = new ArrayList<>();
        for (int i = 11; i <= 20; i++)
            list.add(i);

        Collection<Integer> collection = new ArrayList<>();
        for (int i = 20; i <= 30; i++)
            collection.add(i);

        // Create stream from list
        Stream<Integer> intStream = list.stream();
        Stream<Integer> intStreamCollection = collection.stream();

        // Print the stream
        System.out.println("List: " + list);
        System.out.println("collection: " + collection);

        // Get Slice of Stream
        // containing of elements from the 4th index to 8th
        Stream<Integer>
                sliceOfIntStream = getSliceOfStream(intStream, 4L, 8L);

        Stream<Integer>
                sliceOfIntStreamCollection = getSliceOfStream(intStreamCollection, 4L, 8L);

        // Print the slice
        System.out.println("\nSlice of Stream:");
        sliceOfIntStream.forEach(System.out::println);

        System.out.println("\nSlice of Stream Collection:");
        sliceOfIntStreamCollection.forEach(System.out::println);
    }
}

