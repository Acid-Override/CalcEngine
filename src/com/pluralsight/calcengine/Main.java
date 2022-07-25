package com.pluralsight.calcengine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a letter: ");
        String str = sc.nextLine();
        System.out.println("You have entered: " + str);

        double value1 = 100.0d;
        double value2 = 10.0d;
        double result;

        char opCode = str.charAt(0);

        switch(opCode) {
            case 'a' :
                result = value1 + value2;
                break;
            case 's' :
                result = value1 - value2;
                break;
            case 'm' :
                result = value1 * value2;
                break;
            case 'd' :
                result = value2 != 0 ? value1 / value2 : 0.0d;
                break;
            default:
                System.out.println("Invalid letter: " + opCode);
                result = 0.0d;
        }
        System.out.println(result);
    }
}