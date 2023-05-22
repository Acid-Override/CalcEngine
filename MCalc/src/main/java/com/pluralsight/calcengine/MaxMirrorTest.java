package com.pluralsight.calcengine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxMirrorTest {

    @Test
    void maxMirror() {
        assertEquals(3, new MaxMirror().maxMirror(1, 2, 3, 8, 9, 3, 2, 1));  //→ 3
//        maxMirror([1, 2, 1, 4]) //→ 3
//        maxMirror([7, 1, 2, 9, 7, 2, 1]) //→ 2
    }
}