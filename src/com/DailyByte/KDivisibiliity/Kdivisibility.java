package com.DailyByte.KDivisibiliity;

import lombok.*;

/**
 * Given an integer array, nums, and a value, k, return the total number of contiguous subarrays that are divisible by k.
 *
 * Ex: Given the following nums and k…
 *
 * nums = [1, 3, 1, 2, 5], k = 7, return 2 ([1, 3, 1, 2] and [2, 5] both sum to 7).
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Kdivisibility {

    private int[] nums;
    private int k;
    private int count=0;

    public int kDivis() {

        for (int i=0; i < nums.length; i++) {
            int j = i + 1;
            int sum = nums[i];
            if(sum == k) {
                count++;
                continue;
            }
            while (j != nums.length){
                sum += nums[j];
                j++;
                if(sum == k) {
                    count++;
                    break;
                }

                if(sum > k) {
                    break;
                }
            }

        }



        return count;
    }

}
