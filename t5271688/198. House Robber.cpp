class Solution
{
    int rob(vector<int> &nums, int n, vector<int> &dp)
    {
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = max(nums[n-1] + rob(nums, n - 2, dp), rob(nums, n - 1, dp));
    }

public:
    int rob(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> dp(n + 1, -1);
        return rob(nums, n, dp);
    }
};