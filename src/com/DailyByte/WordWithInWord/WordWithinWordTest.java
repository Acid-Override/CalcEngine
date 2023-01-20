package com.DailyByte.WordWithInWord;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordWithinWordTest {

    @Test
    void wordWithinWordTest() {
        WordWithinWord www = new WordWithinWord();
        String[] result = www.wordWithinWord(new String[]{"Hello World", "ello", "orl"});
        assertArrayEquals(new String[]{"ello", "orl"}, result);
    }
}