package com.pluralsight.calcengine;

public class FizzBuzz {
    public static void main(String[] args) {
        String result;


        for (var i = 0; i <= 100; i++ ) {
            result = "";
            if ( i % 3 == 0 ) {
                result += "Fizz";
            }
            if ( i % 5 == 0 ) {
                result += "Buzz";
            }

            //find a better return statement
            if ( result.length() > 0 ){
                System.out.println(result);
            } else {
                System.out.println(i);
            }
        }
    }
}


