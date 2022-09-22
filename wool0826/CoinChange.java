package org.example;

import java.util.Arrays;

public class CoinChange {
    private int[] dp;

    public int coinChange(int[] coins, int amount) {
        dp = new int[amount + 1];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        int result = process(coins, amount);
        return (result == Integer.MAX_VALUE ? -1 : result);
    }

    private int process(int[] coins, int amount) {
        if (amount < 0) return Integer.MAX_VALUE;
        if (dp[amount] != -1) return dp[amount];

        int minimum = Integer.MAX_VALUE;

        for (int coin : coins) {
            int result = process(coins, amount - coin);
            if (result == Integer.MAX_VALUE) continue;

            minimum = Math.min(minimum, result + 1);
        }

        return dp[amount] = minimum;
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        System.out.println(solution.coinChange(new int[] { 1, 2, 5 }, 10));
    }
}




