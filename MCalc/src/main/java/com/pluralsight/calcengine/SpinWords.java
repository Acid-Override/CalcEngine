package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpinWords {

    public static void main (String[] args) {
        log.info(spinWords("This is another test"));
    }

    public static String spinWords (String str) {


        String[] strArr = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for ( String word: strArr) {
            if ( word.length() >= 5 ) {
                sb.append(reverseWord(word) + " ");
            } else {
                sb.append(word + " ");
            }
        }
        return sb.toString().trim();
    }

    private static String reverseWord(String word) {
        StringBuffer sb = new StringBuffer((word));
        return sb.reverse().toString();
    }

}
