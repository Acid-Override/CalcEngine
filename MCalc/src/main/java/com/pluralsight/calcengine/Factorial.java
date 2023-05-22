package com.pluralsight.calcengine;

import java.util.Scanner;

public class Factorial {

    public static void factorial() {

        System.out.println("Please enter a positive whole number greater than 1 :");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println("Your number is : " + num);
        int result = 1;

        while ( num > 0 ) {
            System.out.println(num);
            result *= num--;
        }
        System.out.println("Your result is :" + result);


    }
}
