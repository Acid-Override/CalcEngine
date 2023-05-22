package com.pluralsight.calcengine;

public class StringEquality {

    public static void main(String[] args){
        System.out.println("String Equality, double quotes is String, single quotes is Char");
        String str1 = "Hello " + "World!";
        int num = 5;
        System.out.println(str1 + num);

        String str2 = "Hello " + "World!";

        //Can't use == to compare strings, str1 & str2 are reference values
//        if ( str1 == str2 ) {
//            System.out.println("String str1 is equal to str2");
//        } else {
//        System.out.println("String str1 IS NOT EQUAL to str2");
//        }

        if (str1.equals(str2)) {
            System.out.println("They are EQUAL! :)");
        }
        String[] stringArr = str1.split(" ");


        //Interning a string
          //provides a canonicalized value
          //enables reliable == operator comparison
          //improves performance in case of frequently compared strings

        String s3 = str1.intern();
        String s4 = str2.intern();

        if ( s4 == s3 ) {
            System.out.println("THEY ARE EQUAL !!");
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
        System.out.println(str1);
        if ( str1.contains("!")){
        int index = str1.indexOf("!");
        String sub = str1.substring(index + 1);
        System.out.println(sub);
        }

        String strNum = String.valueOf(num);
        System.out.println("My new string " + strNum);

        //implicit conversion
        int i = 4, j = 10;
        System.out.println("My two numbers are : " + i + " & " + j + ".");

    }
}
