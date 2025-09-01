package com.playground.DynamicProgramming.Fibonacci;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibonacciTest {
    Fibonacci fib;

    @BeforeAll
    void init() {
        fib = new Fibonacci();
    }

    // [1, 1, 2, 3, 5, 8, 13, 21, 34]
    // [1, 2, 3, 4, 5, 6, 7,  8,  9 ] (n)

    @Test
    void fib() {
        int result = fib.fib(5);
        log.info("Result = {}", result);
        log.info("Actual={}  Expected={}", result, 5);
        assertEquals(5, result);
    }

    @Test
    void fibBigNumber() {

    }
}