package com.DailyByte.UpdateNumbers;

import lombok.extern.slf4j.Slf4j;


import java.util.Arrays;

/**
 * Given an array of integers, nums, return the minimum number of operations needed to make every value in nums unique.
 * Note: An operation consists of selecting a value in nums and incrementing it by one.
 *
 * nums = [2, 2, 1, 3], return 2 (increment one of the twos twice or increment one 2 once and the 3 once).
 */
@Slf4j
public class UpdateNumbers extends Object {

    private Integer count = 0;

    //recursively call the function on array, each time changing a dup numbers

    public Integer updateNumbers(int[] arr) {
        Integer count = 0;
        Arrays.sort(arr);

        if(isArrayDistinct(arr))
            return 0;

        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] == arr[i+1]) {
                arr[i + 1]++;
                count += updateNumbers(arr) + 1;
                return count;
            }
        }
        return count;
    }

    private boolean isArrayDistinct(int[] arr) { //2,931,792 vs 8,652,708
//        return arr.length == Arrays.stream(arr).distinct().count();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1])
                return false;
        }
        return true;
    }

    public int solution(int[] nums) {
        Arrays.sort(nums);
        int operations = 0;
        int previous = 0;
        for (int num: nums) {
            operations += Math.max(previous - num, 0);
            previous = Math.max(previous, num) + 1;
        }

        log.info("Solution :" + operations);
        return operations;
    }
}
