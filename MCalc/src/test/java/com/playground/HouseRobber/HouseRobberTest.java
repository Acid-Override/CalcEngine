package com.playground.HouseRobber;

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
}