package com.playground;

public class IsPrime {
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrimeRecursive(int number) {
        return isPrimeRecursive(number, 2);
    }

    private boolean isPrimeRecursive(int number, int divisor) {
        if (divisor <= 2) {
            return true;
        } else if (number % divisor == 0) {
            return false;
        } else if (divisor * divisor > number) {
            return true;
        }
        return isPrimeRecursive(number, divisor + 1);
    }
}
