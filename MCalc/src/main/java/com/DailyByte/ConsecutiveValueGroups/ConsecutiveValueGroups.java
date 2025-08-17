package com.DailyByte.ConsecutiveValueGroups;

import java.util.*;

/**
 * Given an array of integers, nums, and a value k, return whether or not the nums can be split into k groups such that all values within the group are consecutive.
 *
 * Ex: Given the following nums and k…
 *
 * nums = [2, 4, 1, 3], k = 2, return true (the nums can be split into two groups [1, 2] and [3, 4]).
 * Ex: Given the following nums and k…
 *
 * nums = [5, 3, 10], k = 3, return false.
 */
public class ConsecutiveValueGroups {

    public boolean consecutiveValueGroups(int[] arr, int k) {
        if(arr.length < 4)
            return false;

        Arrays.sort(arr);
        int step = 0;
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 1; i < k; i++ ) {
            List<Integer> grouping = new ArrayList<>();
            while(((step + 1) < (arr.length - 1)) && arr[step + 1] - arr[step] == 1) {
                grouping.add(arr[step]);
                grouping.add(arr[step + 1]);
                step += 2;
            }
            step++;
            list.add(grouping);
        }

        return list.size() == k;
    }

    //building up arrays of k values
    //each array must have consecutive numbers (matching or otherwise)
    //{{1, 2}, {1, 2}} = true k = 2
    //{{1, 2}, {5, 6}} = true k = 2
    //{{1, 2, 3}, {6, 7, 8}} = true k = 3

    public boolean cvg(int[] arr, int k){

        List<List<Integer>> accArr = new ArrayList<>();
        List<Integer> seen = new ArrayList<>();

        for (int i = 0; i < (arr.length - k); i++) {
            if(seen.contains(i))
                continue;
            List<Integer> values = new ArrayList<>();
            values.add(arr[i]);
            for(int j = 0; j < (k-1); j++) {

                int index = Arrays.binarySearch(arr, values.get(j) + 1);
                if(index > 0) {
                    seen.add(index);
                    values.add(arr[index]);
                } else {
                    return false;
                }
            }
            accArr.add(values);
        }
        return true;
    }



    public boolean solution (int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num: nums){
            heap.add(num);
        }
        while (!heap.isEmpty()) {
            int start = heap.remove();
            for (int i = 1; i < k; i++) {
                if (!heap.remove(start + i)) {
                    return false;
                }
            }
        }

        return true;
    }
}
