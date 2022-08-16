package com.pluralsight.calcengine;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BinaryArrayToNumberTest {

    @Test
    void convertBinaryArrayToNumber() {
        assertEquals(1, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(0,0,0,1))));
        assertEquals(15, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(1,1,1,1))));
        assertEquals(6, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(0,1,1,0))));
        assertEquals(9, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(1,0,0,1))));
    }

    @Test
    void convertBinaryArrayToInt() {
        assertEquals(1, BinaryArrayToNumber.convertBinaryArrayToInt(new int[] {0, 0, 0, 1}));
        assertEquals(15, BinaryArrayToNumber.convertBinaryArrayToInt(new int[] {1, 1, 1, 1}));
        assertEquals(6, BinaryArrayToNumber.convertBinaryArrayToInt(new int[] {0, 1, 1, 0}));
        assertEquals(9, BinaryArrayToNumber.convertBinaryArrayToInt(new int[] {1, 0, 0, 1}));
    }

    @Test
    void convertBinaryArrayToIntStreamAndReduce() {
        assertEquals(1, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(0,0,0,1))));
        assertEquals(15, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(1,1,1,1))));
        assertEquals(6, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(0,1,1,0))));
        assertEquals(9, BinaryArrayToNumber.convertBinaryArrayToNumber(new ArrayList<>(Arrays.asList(1,0,0,1))));
    }

}
