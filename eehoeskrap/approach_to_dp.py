'''
https://leetcode.com/problems/climbing-stairs/
'''
class Solution(object):
    memo = {}
    def climbStairs(self, n):
        if n == 1:
            return 1
        
        self.memo[n] = [0] * (n+1)
        self.memo[1] = 1
        self.memo[2] = 2
        
        for i in range(3, n+1):
            self.memo[i] = self.memo[i-1] + self.memo[i-2]
        
        return self.memo[n]

if __name__ == "__main__":
    n = 3
    res = Solution().climbStairs(n)
    print(res)


    
'''
https://leetcode.com/problems/house-robber/
'''
class Solution(object):
    def rob(self, nums):

        if len(nums) == 1:
            return nums[0]
        
        dp = [0] * len(nums)
        
        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])
        
        for i in range(2, len(nums)):
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])
            
        return dp[-1]

if __name__ == "__main__":
    nums = [2,7,9,3,1]
    res = Solution().rob(nums)
    print(res)
    
    

'''
https://leetcode.com/problems/min-cost-climbing-stairs/
'''
class Solution(object):

    def minCostClimbingStairs(self,cost):

        if len(cost) == 1:
            return cost[0]
        
        dp = [0] * len(cost)
        
        dp[0] = cost[0]
        dp[1] = cost[1]
        
        for i in range(2, len(cost)):
            dp[i] = min(dp[i-1], dp[i-2]) + cost[i]
        
        last = len(cost) - 1
    
        return min(dp[last], dp[last-1])

if __name__ == "__main__":
    cost = [10,15,20]
    res = Solution().minCostClimbingStairs(cost)
    print(res)
