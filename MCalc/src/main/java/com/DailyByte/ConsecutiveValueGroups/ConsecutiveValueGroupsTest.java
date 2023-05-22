package com.DailyByte.ConsecutiveValueGroups;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsecutiveValueGroupsTest {

    ConsecutiveValueGroups cvg;

    @BeforeEach
    void setUp() {
        cvg = new ConsecutiveValueGroups();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solutionFirstTest() {
        ConsecutiveValueGroups cvg = new ConsecutiveValueGroups();
        int[] arr = {2, 4, 1, 3};
        int k = 2;
        cvg.solution(arr, k);
    }

    @Test
    void solutionSecondTest() {
        int[] arr = {4, 3, 10};
        int k = 3;
        cvg.solution(arr, k);
    }

    @Test
    void solutionThirdTest() {
        int[] arr = {5, 2, 4, 1};
        int k = 2;
        cvg.solution(arr, k);
    }
    @Test
    void solutionFourthTest() {
        int[] arr = {1, 2, 3, 6, 7, 8};
        int k = 2;
        assertFalse(cvg.solution(arr, k));
    }

    @Test
    void cvgTest() {
        int[] arr = {1, 2, 5, 6};
        int k = 2;
        assertTrue(cvg.cvg(arr, k));
    }
    @Test
    void cvgAltTest() {
        int[] arr = {1, 1, 2, 2};
        int k = 2;
        assertTrue(cvg.cvg(arr, k));
    }
    @Test
    void cvgThirdTest() {
        int[] arr = {1, 2, 3, 6, 7, 8};
        int k = 2;
        assertFalse(cvg.cvg(arr, k));
    }

    @Test
    void solutionFifthTest() {
        int[] arr = {1, 1, 2, 2};
        int k = 2;
        cvg.solution(arr, k);
    }
    @Test
    void cvgInitTest() {
        int[] arr = {2, 4, 1, 3};
        int k = 2;
        cvg.consecutiveValueGroups(arr, k);
    }
}