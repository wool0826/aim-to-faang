//coinChange 해결 완료
class Solution {

    private int[] memo;
    private int[] coins;

    public int coinChange(int[] coins, int amount) {

        if(amount == 0)
            return 0;

        memo = new int[amount + 1];

        this.coins = coins;

        return dp(amount);
    }

    private int dp(int amount){

        if(amount == 0)
            return 0;
        else if(amount < 0)
            return -1;
        if(memo[amount] == 0){
            int min = Integer.MAX_VALUE;
            boolean check = false;
            for(int coin : coins){
                int result = dp(amount - coin);
                if(result >= 0){
                    min = Math.min(min, result + 1);
                    check = true;
                }
            }
            if(check)
                memo[amount] = min;
            else
                memo[amount] = -1;
        }


        return memo[amount];
    }
}


//LIS 해결중
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        int[] a = new int[n+1];
        String str = "asdf";
        Integer.parseInt(str.substring())
        a[0] = Integer.MIN_VALUE;
        for(int i = 1; i < n+1; i++){
            a[i] = nums[i-1];
        }

        for(int i = n; i >= 0; i--){
            dp[i] = 1;
            for(int j = i+1; j <= n; j++){
                if(a[j] > a[i]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        return dp[0] - 1;
    }
}