package com.DailyByte.CorrectCapitalization;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrectCapitalizationTest {

    @Test
    void correctCapitalizationTest() {
        CorrectCapitalization cc = new CorrectCapitalization();
        cc.correctCapitalization("HEllO");
    }
}