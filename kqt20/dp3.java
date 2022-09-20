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
        int[] dp = new int[n];
        int[] mins = new int[n];

        dp[n-1] = 1;
        mins[n-1] = nums[n-1];

        for(int i = n-2; i >= 0; i--){
            if(nums[i] < nums[i+1]){
                if(mins[i+1] > nums[i]){
                    dp[i] = dp[i+1] + 1;
                    mins[i] = nums[i];
                }
                else{
                    dp[i] = dp[i+1];
                    mins[i] = mins[i+1];
                }
            }
            else{
                boolean check = false;
                for(int j = i+1; j < n; j++){
                    if(nums[i] < nums[j]){
                        if(dp[i+1] <= dp[j] + 1){
                            dp[i] = dp[j] + 1;
                            mins[i] = nums[i];
                        }
                        else {
                            dp[i] = dp[i+1];
                            mins[i] = mins[i+1];
                        }
                        check = true;
                    }
                }
                if(!check){
                    dp[i] = dp[i+1];
                    mins[i] = mins[i+1];
                }
            }
        }

        return dp[0];

    }
}