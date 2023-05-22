package com.linkedinlearning.JavaArrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateRightTest {

    @Test
    void rotateRight() {
        Object[] arr = new Integer[] {1, 2, 3, 4, 5};

        assertArrayEquals(new Integer[]{5, 1, 2, 3, 4}, RotateRight.rotateRight(arr));

    }
}