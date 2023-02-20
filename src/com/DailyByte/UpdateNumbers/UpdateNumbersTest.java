package com.DailyByte.UpdateNumbers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateNumbersTest {

//    Logger log = Logger.getLogger(UpdateNumbersTest.class);
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @BeforeEach
    void setUp() {
        log.info("SETUP");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateNumbers() {
        UpdateNumbers un = new UpdateNumbers();
        int[] arr = {2, 2, 3, 1, 4};
        int[] clone = arr.clone();
        int result = un.updateNumbers(arr);
        int solution = un.solution(clone);

        assertEquals(solution, result);
    }
    @Test
    void updateNumbersBasic() {
        UpdateNumbers un = new UpdateNumbers();
        int[] arr = {2, 2, 3, 1, 4};
        un.updateNumbers(arr);
    }
    @Test
    void updateNumbersDistinctCheck() {
        UpdateNumbers un = new UpdateNumbers();
        int[] arr = {2, 4, 1, 3, 5};
        int[] clone = arr.clone();
        assertEquals(un.solution(clone), un.updateNumbers(arr));
    }

    @Test
    void solution() {
        UpdateNumbers un = new UpdateNumbers();
        int[] arr = {2, 2, 3, 1, 4};
        int[] clone = arr.clone();
        assertEquals(un.solution(clone), un.updateNumbers(arr));
    }
    @Test
    void updateNumbersLargeArray() {
        UpdateNumbers un = new UpdateNumbers();
        int[] arr = {4, 6, 8, 3, 2, 1, 6, 11, 14, 18, 77, 44, 66, 52, 4, 88, 64, 42, 65, 82, 85, 23};
        int result = un.updateNumbers(arr);
        System.out.println(result);

    }
    @Test
    void updateNumbersTrickyArray() {
        UpdateNumbers un = new UpdateNumbers();
        int[] arr = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2,3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5,5, 5, 5, 5, 5, 5, 6, 6, 6, 7,
        11, 11, 22, 22, 22, 22, 23, 23, 23, 23, 23, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 27, 27, 27, 28, 28};
        int[] clone = arr.clone();
        long startTime = System.nanoTime();
        int updateNumsResult = un.updateNumbers(arr);
        long endTime = System.nanoTime();
        System.out.println("Update Numbers time : " + (endTime - startTime));
        startTime = System.nanoTime();
        int solutionResult = un.solution(clone);
        endTime = System.nanoTime();
        System.out.println("Solution time : " + (endTime - startTime));

        assertEquals(solutionResult, updateNumsResult);
    }


}