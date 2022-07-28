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
    public static int recursiveSearch(int[] arr, int target, int min, int max) {
        if ( max > min && min < arr.length ) {
            int mid = (int) Math.floor((max + min) / 2 );

            System.out.println(arr[mid]);

            if ( arr[mid] == target) {
                return mid;
            }

            if ( arr[mid] > target) {
                return recursiveSearch(arr, target, min, mid - 1);
            }
            return recursiveSearch(arr, target, mid + 1, max);


        }
        return -1;
    }

    public static void main(String args[]) {
        int[] testArr = new int[520000];
        for (var i = 0; i < 520000; i++ ) {
            testArr[i] = i;
        }

        search(testArr, 1);

        recursiveSearch(testArr, 1, 0, testArr.length);
    }
}
