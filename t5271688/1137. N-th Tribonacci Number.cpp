class Solution
{
    int tribonacci(int n, vector<int> &dp)
    {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = tribonacci(n - 1, dp) + tribonacci(n - 2, dp) + tribonacci(n - 3, dp);
    }

public:
    int tribonacci(int n)
    {
        vector<int> dp(n + 1, -1);
        return tribonacci(n, dp);
    }
};