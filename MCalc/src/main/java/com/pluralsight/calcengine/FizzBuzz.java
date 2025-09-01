package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            if (!result.isEmpty()){
                log.info(result);
            } else {
                log.info(String.valueOf(i));
            }
        }
    }
}


