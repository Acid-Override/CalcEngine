package com.DailyByte.LeastUniqueElements;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class LeastUniqueElementsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void leastUnique() {

        LeastUniqueElements lue = new LeastUniqueElements(List.of(1, 4, 3, 3), 2);
        int result = lue.leastUnique();
        log.info("Result:{}", result);
        assertEquals(1, result);

    }
}