package com.linkedinlearning.JavaArrays;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PracticeArraysTest {

    @Test
    void findSecondSmallestItem() {
        Integer[] arr = new Integer[]{5, 8, 3, 2, 6};
        assertEquals(3, PracticeArrays.findSecondSmallestItem(arr));

        Integer[] arr2 = new Integer[]{3, 8, 5, 2, 6};
        assertEquals(3, PracticeArrays.findSecondSmallestItem(arr2));

        Integer[] arr3 = new Integer[]{6, 8, 5, 2, 3};
        assertEquals(3, PracticeArrays.findSecondSmallestItem(arr3));

        Integer[] arr4 = new Integer[]{3, 3, 3, 3, 3, 3};
        assertNull(PracticeArrays.findSecondSmallestItem(arr4));
    }
}