package com.playground.MyList.MyListError;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MyListErrorWithCounterTest {

    private static MyListErrorWithCounter<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyListErrorWithCounter<>();
    }

    @Test
    void add() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        log.info("LIST={}", list);
        log.info("SIZE={}", list.getTimesAdded());
        assertEquals(4, list.getTimesAdded(), "List size should be 4 after adding 4 elements");
    }

    @Test
    void addAllIntegers() {
        list.addAll(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110);
        log.info("LIST={}", list);
        log.info("SIZE={}", list.getTimesAdded());
        log.info("LIST SIZE={}", list.size());
        assertEquals(11, list.getTimesAdded(), "List size should be 4 after adding 4 elements");
    }

    @Test
    void addAllStrings() {
        MyListErrorWithCounter<String> list = new MyListErrorWithCounter<>();

        list.addAll("TEN", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY",
                "EIGHTY", "NINETY", "ONE HUNDRED", "ONE HUNDRED AND TEN");
        log.info("LIST={}", list);
        log.info("SIZE={}", list.getTimesAdded());
        log.info("LIST SIZE={}", list.size());
        assertEquals(11, list.getTimesAdded(), "List size should be 4 after adding 4 elements");
    }

    @Test
    void contains() {
        list.addAll(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110);
        log.info("LIST={}", list);
        log.info("TIMES ADDED={}", list.getTimesAdded());
        log.info("LIST SIZE={}", list.size());
        assertTrue(list.contains(110), "List should contain 10");
    }

    @Test
    void size() {
    }
}