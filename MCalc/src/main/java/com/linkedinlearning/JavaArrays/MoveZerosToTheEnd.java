package com.linkedinlearning.JavaArrays;

public class MoveZerosToTheEnd {

    public static int[] moveZerosToTheEnd(int[] arr) {
        //move zeros to end of array
        //do this in place
        // {1, 1, 0, 0, 0, 1, 0}
        // bubble sort with 0 bubbling up to top

        for ( int i = 0; i < arr.length; i++ ) {
            for ( int j = i; j < arr.length - 1; j++ ) {
                if (arr[j] == 0) {
                    int k = j + 1;
                    while(arr[k] == 0 && k < arr.length - 1) {
                        k++;
                        if(k == arr.length - 1 && arr[k] == 0) break;
                    }
                    if (arr[k] != 0)
                        swap(arr, j, k);
                }
            }
        }

        return arr;
    }

    private static void swap(int[] arr, int j, int k) {
        arr[j] = arr[k];
        arr[k] = 0;
    }


    public int[] moveZerosToTheEnd2(int[] arr) {
        // iteration down the array
        // if current value is a zero
          // swap with next digit
          // increment loop by one
          // continue

        for (int i = 0; i < arr.length; i++) {

        }
        return null;
    }


}
