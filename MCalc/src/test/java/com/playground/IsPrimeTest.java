package com.playground;

import com.playground.IsPrime.IsPrime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
    void isPrimeRecursiveTrue() {
        IsPrime isPrime = new IsPrime();
        boolean result = isPrime.isPrimeRecursive(7);
        log.info("7 is prime={}", result);
        assertTrue(result);
    }

    @Test
    void isPrimeRecursiveFalse() {
        IsPrime isPrime = new IsPrime();
        boolean result = isPrime.isPrimeRecursive(8);
        log.info("8 is prime={}", result);
        assertFalse(result);
    }
}