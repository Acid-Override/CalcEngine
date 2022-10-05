package com.linkedinlearning.JavaArrays;

import java.util.Arrays;

public class RotateRight {

    public static Object[] rotateRight(Object[] arr) {
        if (arr == null) {
            return null;
        }

        //code here
        Arrays.stream(arr).forEach(System.out::println);

        //index % length = new location
        //5 % 5 = 0
        //4 % 5 = 1
        //3 % 5 = 2
        //2 % 5 = 3
        //1 % 5 = 4
        //0 % 5 = 5
        // { 1, 2, 3, 4, 5}
        // { 5, 1, 2, 3, 4}

        Object[] result = new Object[arr.length];

        for ( int i = 0; i < arr.length; i++ ){
            result[(i + 1) % result.length] = arr[i];
        }

        return result;
    }
}
