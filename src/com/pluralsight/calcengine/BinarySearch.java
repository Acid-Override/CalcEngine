package com.pluralsight.calcengine;

public class BinarySearch {
    public static int search(int[] arr, int target) {
        int min = 0, max = arr.length - 1;
        int mid = (int) Math.floor( (max + min) / 2 );
        int counter = 0;

        while ( arr[mid] != target && min < max ) {
            counter++;
            System.out.println(arr[mid]);
            if ( arr[mid] > target ) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
            mid = (int) Math.floor((max + min) / 2);
        }
        System.out.println("Total Binary Search hops : " + counter);
        return arr[mid] == target ? mid : -1;
    }
    public static Integer recursiveSearch(int[] arr, int target, int min, int max) {
        //int counter = 0;
        if ( max >= min && min < arr.length ) {
            int mid = (int) Math.floor((max + min) / 2 );

            System.out.println(arr[mid]);

            if ( arr[mid] == target) {
                return mid;
                //return ++counter;
            }

            if ( arr[mid] > target) {
                return recursiveSearch(arr, target, min, mid - 1);
            }
            return recursiveSearch(arr, target, mid + 1, max);
        }

        return -1;
    }

    public static void main(String args[]) {
        int[] testArr = new int[10];
        for (var i = 0; i < 10; i++ ) {
            testArr[i] = i;
        }

        search(testArr, 1);

        int result = recursiveSearch(testArr, 1, 0, testArr.length);
        System.out.println(result);
    }
}
