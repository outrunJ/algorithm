package leetc.top;

public class P322_CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int col = 1; col <= amount; col++) {
            dp[n][col] = -1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[i][rest] = -1;
                if (dp[i + 1][rest] != -1) {
                    dp[i][rest] = dp[i + 1][rest];
                }
                if (rest - coins[i] >= 0 && dp[i][rest - coins[i]] != -1) {
                    if (dp[i][rest] == -1) {
                        dp[i][rest] = dp[i][rest - coins[i]] + 1;
                    } else {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - coins[i]] + 1);
                    }
                }
            }
        }
        return dp[0][amount];
    }
}
