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








    }
}
