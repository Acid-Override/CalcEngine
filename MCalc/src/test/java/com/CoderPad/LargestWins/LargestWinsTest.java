package com.CoderPad.LargestWins;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LargestWinsTest {

    @Test
    void findLargest() {
        LargestWins largestWins = new LargestWins();
        assertEquals(5, largestWins.findLargest(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void findLargest2() {
        LargestWins largestWins = new LargestWins();
        int max = largestWins.findLargest(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100));
        log.info("{}", max);
        assertEquals(100, max);
    }

    @Test
    void findLargest3() {
        LargestWins largestWins = new LargestWins();
        int max = largestWins.findLargestClassical(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100));
        log.info("{}", max);
        assertEquals(100, max);
    }
}