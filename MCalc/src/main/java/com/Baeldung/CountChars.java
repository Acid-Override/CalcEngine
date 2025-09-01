package com.Baeldung;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class CountChars {

    static String someString = "elephant";

    public static void main (String... args) {
        long count = someString.chars().filter(ch -> ch == 'e').count();
        log.info("Count:{}", count);

        long count2 = someString.codePoints().filter(ch -> ch == 'e').count();
        log.info("Count2:{}", count2);

//        int count3 = StringUtils.countMatches(someString, "e");
//        log.info(count3);
//
//        String reverseSomeString = StringUtils.reverse(someString);
//        log.info(reverseSomeString + " " + someString);

    }

}
