package com.DailyByte;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class OrderedDigitsTest {

    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

//    @BeforeEach
//    void setUp() {
//    }

    @AfterEach
    void tearDown() {
        log.info("TearDown");
    }

    @Test
    void orderedDigits() {
        OrderedDigits od = new OrderedDigits(10, 20);
        List<Integer> result = new ArrayList<>(List.of(12));
        assertEquals(result, od.orderedDigits());
    }
    @Test
    void orderedDigitsLongTest() {
        OrderedDigits od = new OrderedDigits(0, 100);
        List<Integer> result = new ArrayList<>(List.of(12, 23, 34, 45, 56, 67, 78, 89));
        log.info(result.toString());
        assertEquals(result, od.orderedDigits());
    }
    @Test
    void ordereDigitsMaxTest() {
        OrderedDigits od = new OrderedDigits(0, 1_000_000_000);
        od.orderedDigits();
    }
}