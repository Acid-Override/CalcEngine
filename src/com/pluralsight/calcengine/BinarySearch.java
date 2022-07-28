package com.pluralsight.calcengine;

public class BinarySearch {
    public static int search(int[] arr, int target) {
        int min = 0, max = arr.length - 1;
        int mid = (int) Math.floor( (max + min) / 2 );

        while ( arr[mid] != target && min < max ) {
            System.out.println(arr[mid]);
            if ( arr[mid] > target ) {
                max = mid + 1;
            } else {
                min = mid - 1;
            }
            mid = (int) Math.floor((max + min) / 2);
        }
        return arr[mid] == target ? mid : -1;
    }
    public static int recursiveSearch(int[] arr, int target) {
        //recursive code here
        //recursion is funnnnnnnn
    }
}
