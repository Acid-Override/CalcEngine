package com.playground.CountAllVowels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountAllVowelsTest {

    @Test
    void countAllVowels() {
        CountAllVowels snc = new CountAllVowels();
        Integer result = snc.countAllVowels("ABCDEF HIJKL");
        assertEquals(3, result);

    }
}