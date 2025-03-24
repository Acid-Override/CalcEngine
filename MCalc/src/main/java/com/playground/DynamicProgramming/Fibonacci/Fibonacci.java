package com.playground.DynamicProgramming.Fibonacci;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Fibonacci {

    public int fib(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        log.info("Fibonacci of {} is {}", n, dp[n-1]);
        return dp[n-1];
    }
}
