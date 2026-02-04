package com.ESPN.TwoSum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    // classic solution is to set up a nested loop but this would be O(n^2)

    // better approach is to create hashmap using Value:Index as key:val pairs
    // Iterate through array and check map for compliment, if it exists, return Map val (index) and current index

    // Create a Hashmap for of seen values:indexes and initialize it
    // iterate through list of numbers
        // calculate the compliment (target - current value)
        // if seen contains compliment
            // return solution
        // else
            // add current value:index to seen map
    // return Collections.empty() for no solutions found

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int compliment = target - nums[i];
            if (seen.containsKey(compliment)) {
                return new int[]{seen.get(compliment), i};
            }
            seen.put(nums[i], i);
        }

        return new int[]{};
    }
}
