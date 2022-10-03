package com.Baeldung;

// https://www.baeldung.com/java-count-chars

// import commons.lang.StringUtils;

import org.apache.commons.lang3.StringUtils;

public class CountChars {

    static String someString = "elephant";

    public static void main (String... args) {
        long count = someString.chars().filter(ch -> ch == 'e').count();
        System.out.println(count);

        long count2 = someString.codePoints().filter(ch -> ch == 'e').count();
        System.out.println(count2);

        int count3 = StringUtils.countMatches(someString, "e");
        System.out.println(count3);

        String reverseSomeString = StringUtils.reverse(someString);
        System.out.println(reverseSomeString + " " + someString);



    }

}
