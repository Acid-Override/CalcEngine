package com.pluralsight.calcengine;

public class CalculateInterest {

    private static double amt;
    private static double rate;
    private static int years;

    public static double calculateInterest(double amt, double rate, int years) {
        CalculateInterest.amt = amt;
        CalculateInterest.rate = rate;
        CalculateInterest.years = years;
        return amt * rate * years;
    }
    static double[] produceInterestHistory(double amt, double rate, int years) {
        double[] accumulatedInterest = new double[years + 1];
        accumulatedInterest[0] = amt;
        for ( var i = 1; i <= years; i++ ) {

            accumulatedInterest[i] = calculateInterest(accumulatedInterest[i-1], rate, years) + accumulatedInterest[i - 1];
        }
        return accumulatedInterest;
    }
}
