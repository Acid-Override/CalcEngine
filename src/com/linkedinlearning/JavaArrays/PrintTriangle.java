package com.linkedinlearning.JavaArrays;

import java.util.Arrays;

public class PrintTriangle {

    public static void printTriangle(Object[] arr) {

        for ( int i = 0; i < arr.length; i++ ) {
            Arrays.stream(arr).forEach(System.out::println);
        }

    }

}
