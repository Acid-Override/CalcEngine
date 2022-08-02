package com.pluralsight.calcengine;

class Solution {

    public Solution() {
        int i = 0;
    }
     public static int[] twoSum(int[] nums, int target) {


        int i = 1;
        int[] result = new int[2];

        while(nums.length - i >= 0) {
        int index1 = nums.length - i;
        int num2 = target - nums[index1];
        i++;
        for (int j = 0; j < nums.length; j++ ) {
            if (index1 != j && nums[j] == num2) {
                result[0] = j;
                result[1] = index1;
                String str = String.format("%d and %d", result[0], result[1]);
                System.out.println(str);
                return result;
            }
        }
    }

        return result;
    }
}