package com.playground.HouseRobber;

import com.playground.DynamicProgramming.HouseRobber.HouseRobber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseRobberTest {

    @Test
    void rob() {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {2, 7, 9, 3, 1};
        int result = houseRobber.rob(nums);
        assertEquals(12, result);
    }

    @Test
    void rob2() {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {1, 9, 1, 9, 1, 9, 1, 0, 9, 0, 9, 0, 9, 0};
        int result = houseRobber.rob(nums);
        assertEquals(31, result);
    }
}