package com.linkedinlearning.JavaArrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveZerosToTheEndTest {

    @Test
    void moveZerosToTheEnd() {
        MoveZerosToTheEnd moveZerosToTheEnd = new MoveZerosToTheEnd();
        int[] result = MoveZerosToTheEnd.moveZerosToTheEnd(new int[]{0, 1, 0, 3, 12});
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, result);
    }
}