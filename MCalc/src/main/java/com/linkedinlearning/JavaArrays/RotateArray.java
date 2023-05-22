package com.linkedinlearning.JavaArrays;

import java.util.Arrays;
import java.util.Objects;

public class RotateArray {

    public static Object[] rotateArray (Object [] arr) {
        if(Objects.isNull(arr)) return null;
        Object[] result = new Object[arr.length];

        for (int i = 0; i < arr.length; i++ ) {
            result[(i+1) % arr.length] = arr[i];
        }
        Arrays.stream(result).forEach(System.out::println);
        return result;
    }
}


/*
5 % 5 = 0;
4 % 5 = 1;
3 % 5 = 2;
2 % 5 = 3;
1 % 5 = 4;
0 % 5 = 5;
*/

