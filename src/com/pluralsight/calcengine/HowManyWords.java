package com.pluralsight.calcengine;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class HowManyWords {

    private String sentence;
    private Integer counter = 0;

    @Test
    public void firstTest () {
        HowManyWords hmw = new HowManyWords("Hello World");
        //hmw.howManyWords();
        assertEquals(0, 0);
    }

    public static void main (String[] args){
        HowManyWords hmw = new HowManyWords("This is a sentence with seven words.");
        hmw.howManyWords();
    }

    public HowManyWords(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void howManyWords() {
        System.out.println(sentence);
        countWords();
//        PrivateClass pv = new PrivateClass("helper function");
//        pv.privateClassFunction();
    }

    private void countWords() {
        String[] parts = sentence.split(" ");
        for ( int i = 0; i < parts.length; i++ ) {
            System.out.println(parts[i]);
            counter++;
        }
        System.out.println(counter);
    }


    //additional private class
    class PrivateClass {
        private String str;

        PrivateClass() {}
        PrivateClass(String str) {
            this.str = str;
        }

        public void privateClassFunction () {
            System.out.println(str);
            String[] parts = str.split(" ");
            for ( int i = 0; i < parts.length; i++ ) {
                System.out.println(parts[i]);
            }
        }
    }
}
