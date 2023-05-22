package com.linkedinlearning.JavaArrays;

import java.util.Arrays;

public class PrintTriangle {

    public static void printTriangle(Object[] arr) {

//        for ( int i = 0; i < arr.length; i++ ) {
//            Arrays.stream(arr).forEach(System.out::println);
//        }

        for ( int i = 0; i < arr.length; i++ ) {
            for (int j = 0; j <= i; j++ ) {
                System.out.print(arr[j]);
            }
            System.out.println("");
        }
        System.out.println("");

    }

}
