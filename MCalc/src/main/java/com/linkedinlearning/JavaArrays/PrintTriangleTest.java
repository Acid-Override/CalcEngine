package com.linkedinlearning.JavaArrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintTriangleTest {

    @Test
    void printTriangle() {

        PrintTriangle.printTriangle(new Integer[] {1, 2, 3, 4});
        PrintTriangle.printTriangle(new Character[] {'A', 'B', 'C', 'D', 'E'});
        PrintTriangle.printTriangle(new String[] {});
        PrintTriangle.printTriangle(new String[] {"Hello"});
        PrintTriangle.printTriangle(new String[] {"Hello", "There", "Friend"});



    }
}