package com.linkedinlearning.JavaArrays;

public class Reverse {

    public static void reverse(int[] arr) {
        //count up and down at the same time
        //swap items

        for (int i = 0; i < (arr.length / 2); i++) {
            swap(arr, i, arr.length - 1 - i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
