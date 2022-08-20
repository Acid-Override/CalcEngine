package com.pluralsight.calcengine;

import java.util.Arrays;


public class MaxMirror {

    public static void main(String... args) {
        maxMirror(new int[]{1, 2, 3, 8, 9, 3, 2, 1}); //3
        maxMirror(new int[]{1, 2, 1, 4}); //3
        maxMirror(new int[]{7, 1, 2, 9, 7, 2, 1}); //2
    }

    public static int maxMirror(int... nums) {

        if (nums.length == 0) {
            return 0;
        }

        if ( nums.length == 1 ) {
            return 1;
        }

        int result = 0;
       for ( int i = 0; i < nums.length; i++ ) {
           int counter = 0;
           for ( int j = nums.length-1; j > 0; j-- ) {
               if ( nums[i] == nums[j]) {
                   int ai = i;
                   int aj = j;
                   counter = 0;
                   while ( aj >= 0 && ai < nums.length && nums[ai] == nums[aj] ) {
                       counter++;
                       ai++;
                       aj--;
                   }
                   if (counter > result) {
                       result = counter;
                   }
               }

           }
       }
        System.out.println(result);
        return result;
    }
}
