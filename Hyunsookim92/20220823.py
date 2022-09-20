## House Robber

class Solution:
    def rob(self, nums: List[int]) -> int:
        def dp(i):
            if i == 0:
                return nums[0]
            if i == 1:
                return max(nums[0], nums[1])
            if i not in memo:
                memo[i] = max(dp(i - 1), dp(i - 2) + nums[i])
            return memo[i]

        memo = {}
        return dp(len(nums) - 1)
        
## Min Cost Climbing Stairs

class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        def dp(i):
            if i == 0:
                return cost[0]
            if i == 1:
                return cost[1]
            else:
                return cost[i] + min(dp(i - 1), dp(i - 2))

        return min(dp(len(cost) - 1), dp(len(cost) - 2))
        
## N-th Tribonacci Number

class Solution:
    def tribonacci(self, n: int) -> int:
        def dp(i):
            if i == 0:
                return 0
            if i == 1 or i == 2:
                return 1
            else:
                return dp(i - 3) + dp(i - 2) + dp(i - 1)

        return dp(n)
