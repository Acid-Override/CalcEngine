package com.DailyByte.EvenVowel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvenVowelTest {

    @Test
    void evaluateEvenVowelString() {
        EvenVowel ev = new EvenVowel("aeiouaeioua", "aeiou");
        int result = ev.evaluateEvenVowelString();
        assertEquals(10, result);
    }
    @Test
    void evaluateNoVowelString() {
        EvenVowel ev = new EvenVowel("bbb", "aeiou");
        int result = ev.evaluateEvenVowelString();
        assertEquals(3, result);
    }
}