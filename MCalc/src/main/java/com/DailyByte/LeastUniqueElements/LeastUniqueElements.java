package com.DailyByte.LeastUniqueElements;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Given an array of integers, nums, and an integer, k, return the least number of unique numbers left in nums after removing k elements.
 * Note: At least one integer will exist in nums and k will always be between zero and nums.length.
 *
 * Ex: Given the following nums and k…
 *
 * nums = [1, 4, 3, 3], k = 2, return 1 (remove 1 and 4 and only one unique integer is left which is 3).
 * https://thedailybyte.dev/solution/348?token=040e5b78445116f8c2b5d40006b5c9b03c1eb7e56ae90c00da173ad0337136a7
 */
@Slf4j
public class LeastUniqueElements {

    private List<Integer> nums = new ArrayList<>();
    private Integer k;

    public LeastUniqueElements(List<Integer> nums, Integer k) {
        this.nums = nums;
        this.k = k;
    }

    public Integer leastUnique() {

        Map<Integer, Integer> hashMap = new HashMap<>();
        nums.forEach(x -> hashMap.put(x, hashMap.getOrDefault(x, 0) + 1));


        hashMap.entrySet().forEach(System.out::println);
        log.info("FINISHED");

        List<Integer> sorted = new ArrayList<>(hashMap.keySet());
        Collections.sort(sorted, (a, b) -> hashMap.get(a) - hashMap.get(b));
        log.info(sorted.toString());

        //no need to change/modify map or list (this only increase time/complexity)
        int index = 0;
        int removed = 0;
        while (k > 0) {
            k -= hashMap.get(sorted.get(index));
            if (k >= 0) {
                removed++;
            }
        }
        return hashMap.size() - removed;
    }
}
