package com.linkedinlearning.JavaArrays;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.DoubleStream;

@Slf4j
public class PracticeArrays {

    public static void main(String[] args) {
        double[] arr = {92, 12, 38, 74};

        DoubleStream doubleStream = Arrays.stream(arr).map(x -> x + 2);
        doubleStream.forEach(x -> log.info(String.valueOf(x)));
        Arrays.sort(arr);
//        Arrays.stream(arr).forEach(System.out::println);

        int [] nums = new int[2];
        Arrays.stream(nums).forEach(x -> log.info(String.valueOf(x)));

        //double[] lotteryNums = new double[5];
        //primative data types initialize to zero
        //reference data types initialize to null
        double[] lotteryNums = {45, 92, 38, 33, 21};
        log.info(String.valueOf(lotteryNums[2]));

        lotteryNums[2] = 40;
        log.info(String.valueOf(lotteryNums[2]));
    }

    public static Integer findSecondSmallestItem2 (Integer[] arr ) {

        if ( arr.length <= 1) return null;

        Arrays.sort(arr);

        for ( int i = 0; i < arr.length - 1; i++ ) {
            if ( !Objects.equals(arr[i], arr[i + 1]) ) {
                return arr[i + 1];
            }
        }
        return null;
    }

    public static Integer findSecondSmallestItem (Integer[] arr) {

        Integer smallest = Integer.MAX_VALUE;
        Integer secondSmallest = Integer.MAX_VALUE;

        for ( int i = 0; i < arr.length; i++) {
            Integer current = arr[i];
            if ( current < smallest ) {
                secondSmallest = smallest;
                smallest = current;
            } else {
                if(current < secondSmallest && current != smallest) {
                    secondSmallest = current;
                }
            }
        }
        if (secondSmallest == Integer.MAX_VALUE) return null;
        return secondSmallest;
    }
}
