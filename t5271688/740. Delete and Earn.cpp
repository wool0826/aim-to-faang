class Solution
{
    int deleteAndEarn(vector<int> &value, int n, vector<int> &dp)
    {
        if (n <= 0)
            return 0;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = max(value[n] * n + deleteAndEarn(value, n - 2, dp), deleteAndEarn(value, n - 1, dp));
    }

public:
    int deleteAndEarn(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> value(10001, 0);
        for (int i = 0; i < n; i++)
            value[nums[i]]++;
        vector<int> dp(10001, -1);
        return deleteAndEarn(value, 10000, dp);
    }
};