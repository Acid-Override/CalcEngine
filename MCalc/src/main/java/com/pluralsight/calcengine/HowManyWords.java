package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class HowManyWords {

    private final String sentence;
    private Integer counter = 0;

//    @Test
//    public void firstTest () {
//        HowManyWords hmw = new HowManyWords("Hello World");
//        //hmw.howManyWords();
////        assertEquals(0, 0);
//    }

    public static void main (String[] args){
        HowManyWords hmw = new HowManyWords("This is a sentence with seven words.");
        hmw.howManyWords();
    }

    public HowManyWords(String sentence) {
        this.sentence = sentence;
    }

    public void howManyWords() {
        log.info(sentence);
        countWords();
//        PrivateClass pv = new PrivateClass("helper function");
//        pv.privateClassFunction();
    }

    private void countWords() {
        String[] parts = sentence.split(" ");
        for ( int i = 0; i < parts.length; i++ ) {
            log.info(parts[i]);
            counter++;
        }
        log.info(String.valueOf(counter));
    }


    //additional private class
    static class PrivateClass {
        private String str;

        PrivateClass() {}
        PrivateClass(String str) {
            this.str = str;
        }

        public void privateClassFunction () {
            log.info(str);
            String[] parts = str.split(" ");
            for (String part : parts) {
                log.info(part);
            }
        }
    }
}
