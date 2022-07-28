package com.pluralsight.calcengine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        for (String arg : args) {
            System.out.println(arg);
        }

        if (args.length == 0 ) {
            for (int i = 0; i < opCodes.length; i++){
                results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
            }
            for (double i : results) {
                System.out.println(i);
            }
        } else if (args.length == 3) {
            //double result = execute ( args[0].charAt(0), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
            double result = handleCommandLine(args);
            System.out.println(result);
        } else {
            System.out.println("Please provide an operation code and two numeric values.");
        }

        int[] testArr = new int[5200];
        for (var i = 0; i < 5200; i++ ) {
            testArr[i] = i;
        }
        BinarySearch.search(testArr, 1);

        double[] totalAmount = CalculateInterest.produceInterestHistory(100d, .06d, 10);
//        float total = (totalAmount[totalAmount.length - 1]).setScale(2, RoundingMode.CEILING);
        System.out.println("Total amount is : " + totalAmount[totalAmount.length - 1]);
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
}