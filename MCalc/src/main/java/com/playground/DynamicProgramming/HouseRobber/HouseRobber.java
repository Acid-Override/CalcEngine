package com.playground.DynamicProgramming.HouseRobber;


/**
 * Dynamic Programming Problem
 *
 *ou are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 *
 * nums = [2, 7, 9, 3, 1], return 12 (rob houses 2, 9, and 1)
 */
public class HouseRobber {


    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }

    //  dp = [0, 2, 7, 11,11, 12]
    //nums = [2, 7, 9, 3, 1]
    //        0  1  2  3  4  5


}
