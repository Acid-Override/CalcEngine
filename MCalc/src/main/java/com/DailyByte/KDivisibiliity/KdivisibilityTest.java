package com.DailyByte.KDivisibiliity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KdivisibilityTest {

    Kdivisibility kdi;

    @BeforeEach
    void setUp() {
        kdi = new Kdivisibility();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void kDivisInitial() {
        kdi.setNums(new int[] {1, 3, 1, 2, 5});
        kdi.setK(7);
        int result = kdi.kDivis();
        assertEquals(2, result);

    }
}