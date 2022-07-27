package com.pluralsight.calcengine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];


        for (int i = 0; i < opCodes.length; i++)
            switch (opCodes[i]) {
                case 'a' -> results[i] = leftVals[i] + rightVals[i];
                case 's' -> results[i] = leftVals[i] - rightVals[i];
                case 'm' -> results[i] = leftVals[i] * rightVals[i];
                case 'd' -> results[i] = rightVals[i] != 0 ? leftVals[i] / rightVals[i] : 0.0d;
                default -> System.out.println("Invalid letter: " + opCodes[i]);
            }
        for (double i : results) {
            System.out.println(i);
        }
        double totalAmount = CalculateInterest.calculateInterest(100, .06, 10);
        System.out.println("Total amount is : " + totalAmount);
    }
}