package com.playground.MoveZeroToEnd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveZeroToEndTest {

    @Test
    void moveZeroToEnd() {
        MoveZeroToEnd moveZeroToEnd = new MoveZeroToEnd();
        moveZeroToEnd.moveZeroToEnd(new int[]{0, 1, 0, 3, 12});
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, moveZeroToEnd.moveZeroToEnd(new int[]{0, 1, 0, 3, 12}));
    }
}