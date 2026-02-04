package com.ESPN.TwoSum;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwoSumTest {

    static TwoSum ts;

    @BeforeAll
    static void init() {
        ts = new TwoSum();
    }

    @Test
    void testTwoSum() {
        int[] nums = new int[]{2, 7, 8, 5, 1, 6};
        int target = 13;
        int[] result = ts.twoSum(nums, target);
        // Valid pairs: 7+6=13 (indices 1,5) or 8+5=13 (indices 2,3)
        // Algorithm finds first pair: 8 at index 2, complement 5 at index 3
        assert (result[0] == 2 && result[1] == 3);
        log.info("testTwoSum passed: indices [2, 3] for target 13");
    }

    @Test
    void testTwoSumBasicCase() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = ts.twoSum(nums, target);
        assert result[0] == 0 && result[1] == 1;
        log.info("testTwoSumBasicCase passed: indices [0, 1] for target 9");
    }

    @Test
    void testTwoSumNumbersAtEnd() {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        int[] result = ts.twoSum(nums, target);
        assert (result[0] == 1 && result[1] == 2) || (result[0] == 2 && result[1] == 1);
        log.info("testTwoSumNumbersAtEnd passed: indices [1, 2] for target 6");
    }

    @Test
    void testTwoSumSameNumberTwice() {
        int[] nums = new int[]{3, 3};
        int target = 6;
        int[] result = ts.twoSum(nums, target);
        assert result[0] == 0 && result[1] == 1;
        log.info("testTwoSumSameNumberTwice passed: indices [0, 1] for target 6");
    }

    @Test
    void testTwoSumWithNegativeNumbers() {
        int[] nums = new int[]{-1, -2, -3, 5, 10};
        int target = 8;
        int[] result = ts.twoSum(nums, target);
        // -2 + 10 = 8 at indices 1 and 4
        assert result[0] == 1 && result[1] == 4;
        log.info("testTwoSumWithNegativeNumbers passed: indices [1, 4] for target 8");
    }

    @Test
    void testTwoSumWithZero() {
        int[] nums = new int[]{0, 0, 3, 4};
        int target = 0;
        int[] result = ts.twoSum(nums, target);
        assert result[0] == 0 && result[1] == 1;
        log.info("testTwoSumWithZero passed: indices [0, 1] for target 0");
    }

    @Test
    void testTwoSumLargeNumbers() {
        int[] nums = new int[]{1000000, 2000000, 3000000, 4000000};
        int target = 5000000;
        int[] result = ts.twoSum(nums, target);
        // Algorithm finds first valid pair: 2000000 + 3000000 = 5000000 at indices 1 and 2
        assert result[0] == 1 && result[1] == 2;
        log.info("testTwoSumLargeNumbers passed: indices [1, 2] for target 5000000");
    }

    @Test
    void testTwoSumNoSolution() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int target = 100;
        int[] result = ts.twoSum(nums, target);
        assert result.length == 0;
        log.info("testTwoSumNoSolution passed: empty array returned for no solution");
    }

    @Test
    void testTwoSumDuplicateValues() {
        int[] nums = new int[]{1, 5, 7, 1, 2, 8};
        int target = 10;
        int[] result = ts.twoSum(nums, target);
        // 2 + 8 = 10 at indices 4 and 5
        assert result[0] == 4 && result[1] == 5;
        log.info("testTwoSumDuplicateValues passed: indices [4, 5] for target 10");
    }

    @Test
    void testTwoSumFirstAndLastElement() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 9};
        int target = 10;
        int[] result = ts.twoSum(nums, target);
        assert result[0] == 0 && result[1] == 5;
        log.info("testTwoSumFirstAndLastElement passed: indices [0, 5] for target 10");
    }

}