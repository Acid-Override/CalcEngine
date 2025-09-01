package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringEquality {

    public static void main(String[] args){
        log.info("String Equality, double quotes is String, single quotes is Char");
        String str1 = "Hello " + "World!";
        int num = 5;
        log.info(str1 + num);

        String str2 = "Hello " + "World!";

        //Can't use == to compare strings, str1 & str2 are reference values
//        if ( str1 == str2 ) {
//            log.info("String str1 is equal to str2");
//        } else {
//        log.info("String str1 IS NOT EQUAL to str2");
//        }

        if (str1.equals(str2)) {
            log.info("They are EQUAL! :)");
        }
        String[] stringArr = str1.split(" ");


        //Interning a string
          //provides a canonicalized value
          //enables reliable == operator comparison
          //improves performance in case of frequently compared strings

        String s3 = str1.intern();
        String s4 = str2.intern();

        if ( s4 == s3 ) {
            log.info("THEY ARE EQUAL !!");
        }


        //String methods
        //length
        //Create : concat, replace, toLowerCase, toUpperCase, trim, split
        //Extract: charAt, substring
        //Test : contains, endsWith, startsWith, indexOf, lastIndexOf
        //Comparison : equals, equalsIgnoreCase, isEmpty, compareTo, compareToIgnoreCase
        //Formatting: format
        //String conversion : valueOf

        str1 = str1.concat("Ada Lovelace is a legend.");
        log.info(str1);
        if ( str1.contains("!")){
        int index = str1.indexOf("!");
        String sub = str1.substring(index + 1);
        log.info(sub);
        }

        String strNum = String.valueOf(num);
        log.info("My new string " + strNum);

        //implicit conversion
        int i = 4, j = 10;
        log.info("My two numbers are : " + i + " & " + j + ".");

    }
}
