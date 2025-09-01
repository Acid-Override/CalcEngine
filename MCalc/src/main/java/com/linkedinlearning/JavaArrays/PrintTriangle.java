package com.linkedinlearning.JavaArrays;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class PrintTriangle {

    public static void printTriangle(Object[] arr) {

//        for ( int i = 0; i < arr.length; i++ ) {
//            Arrays.stream(arr).forEach(System.out::println);
//        }

        for ( int i = 0; i < arr.length; i++ ) {
            for (int j = 0; j <= i; j++ ) {
                System.out.print(arr[j]);
            }
            log.info("");
        }
        log.info("");

    }

}
