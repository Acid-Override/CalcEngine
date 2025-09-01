package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Factorial {

    public static void factorial() {

        log.info("Please enter a positive whole number greater than 1 :");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        log.info("Your number is : " + num);
        int result = 1;

        while ( num > 0 ) {
            log.info(String.valueOf(num));
            result *= num--;
        }
        log.info("Your result is :" + result);


    }
}
