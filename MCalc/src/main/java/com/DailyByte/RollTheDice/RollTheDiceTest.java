package com.DailyByte.RollTheDice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollTheDiceTest {

    private RollTheDice rtd;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void roll() {
    }

    @Test
    void rollTheDice() {
        rtd = new RollTheDice(2, 6);
        rtd.rollTheDice(4);
    }
}