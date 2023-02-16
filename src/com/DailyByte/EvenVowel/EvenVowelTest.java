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
    @Test
    void evenVowelSubstringInit() {
        EvenVowel ev = new EvenVowel();
        int result = ev.evenVowelSubstring("aeiouaeiouai");
        assertEquals(10, result);
    }

    @Test
    void evenVowelSubstringCheck() {
        EvenVowel ev = new EvenVowel("bbb", "aeiou");
        int result = ev.evaluateEvenVowelString();
        assertEquals(3, result);
    }

    @Test
    void evenVowelSubstringNoVowels() {
        EvenVowel eve = new EvenVowel();
        int result = eve.evenVowelSubstring("bbbbb");

    }

    @Test
    void evaluateOddVowelString() {
        EvenVowel ev = new EvenVowel("aeio", "aeiou");
        int result = ev.evaluateEvenVowelString();
//        assertEquals(10, result);
    }
}