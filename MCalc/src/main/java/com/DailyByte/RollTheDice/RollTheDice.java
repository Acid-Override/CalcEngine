package com.DailyByte.RollTheDice;

/**
 * You are given N dice, where each die has max faces (with values one through max, and an integer, target.
 * Return the total number of ways you can roll the N dice such that the sum of all their face-up values equals the given target.
 *
 * Ex: Given the following, N, max, and target…
 *
 * N = 1, max = 6, target = 5, return 1 (there is only one way to roll the single die to sum to 5 i.e. roll a 5).
 * Ex: Given the following, N, max, and target…
 *
 * N = 2, max = 6, target = 4, return 3.
 */
public class RollTheDice {

    private int N;
    private int max;

    public RollTheDice(int n, int max) {
        N = n;
        this.max = max;
    }

    public int roll(int target) {

        int count = 0;


        return 0;
    }

    public int rollTheDice( int target) {
        int[][] dp = new int[N + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= target; j++) {
                for (int k = 1; k <= max; k++) {
                    if (j >= k) {
                        dp[i][j] += dp[i - 1][j - k];
                    } else {
                        break;
                    }
                }
            }
        }

        return dp[N][target];
    }

}
