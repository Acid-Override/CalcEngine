package com.playground;

import com.playground.IsPrime.IsPrime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsPrimeTest {

    @Test
    void isPrime() {
        assertTrue(IsPrime.isPrime(2));
    }

    @Test
    void testIsPrime() {
        assertTrue(IsPrime.isPrime(7));
    }

    @Test
    void testIsPrimewithNonPrime() {
        assertFalse(IsPrime.isPrime(14));
    }

    @Test
    void isPrimeRecursive() {
        IsPrime isPrime = new IsPrime();
        boolean result = isPrime.isPrimeRecursive(7);
        assertTrue(result);
    }
}