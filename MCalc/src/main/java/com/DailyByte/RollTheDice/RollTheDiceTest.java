package com.DailyByte.RollTheDice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
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

    @Test
    void rollManyDice() {
        rtd = new RollTheDice(8, 6);
        int result = rtd.rollTheDice(8*3);
        log.info("RESULT+{}", result);
    }
}