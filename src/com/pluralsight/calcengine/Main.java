package com.pluralsight.calcengine;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Factorial.factorial();
        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        for (String arg : args) {
            System.out.println(arg);
        }

        if (args.length == 0 ) {
            executeInteractively();
//            for (int i = 0; i < opCodes.length; i++){
//                results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
//            }
//            for (double i : results) {
//                System.out.println(i);
//            }
        } else if (args.length == 3) {
            //double result = execute ( args[0].charAt(0), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
            double result = handleCommandLine(args);
            System.out.println(result);
        } else {
            System.out.println("Please provide an operation code and two numeric values.");
        }

        int[] testArr = new int[520];
        for (var i = 0; i < 520; i++ ) {
            testArr[i] = i;
        }
        //BinarySearch.search(testArr, 1);

        double[] totalAmount = CalculateInterest.produceInterestHistory(100d, .06d, 10);
//        float total = (totalAmount[totalAmount.length - 1]).setScale(2, RoundingMode.CEILING);
        System.out.println("Total amount is : " + totalAmount[totalAmount.length - 1]);

    }

    static void executeInteractively () {
        System.out.println("Please enter a modifier and two numbers: ");
        Scanner sc = new Scanner(System.in);
        String arthmeticString = sc.nextLine();
        String[] array = arthmeticString.split(" ");
        char opCode = opCodeFromString(array[0]);
        double leftVal = valueFromString(array[1]);
        double rightVal = valueFromString(array[2]);
        double result = execute(opCode, leftVal, rightVal);ad
        System.out.println(array[0] + " " + array[1] + " & " + array[2] + " = " + result);
    }


    private static double handleCommandLine(String[] args) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(opCode, leftVal, rightVal);
        return result;
    }

    static double execute(char opCode, double leftVal, double rightVal){
        double result = 0.0;
        switch (opCode) {
            case 'a' -> result = leftVal + rightVal;
            case 's' -> result = leftVal - rightVal;
            case 'm' -> result = leftVal * rightVal;
            case 'd' -> result = rightVal != 0 ? leftVal / rightVal : 0.0d;
            default -> System.out.println("Invalid letter: " + opCode);
        }
        return result;
    }
    static char opCodeFromString(String operationName ){
        return operationName.toLowerCase().charAt(0);
    }
    static double valueFromString(String str){
        String[] stringOfValues = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        double value = 0d ;
        for ( int i = 0; i < stringOfValues.length; i++ ) {
            if ( str.equals(stringOfValues[i])) {
                value = i;
                break;
            }
        }
        return value;
    }
}