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
}
