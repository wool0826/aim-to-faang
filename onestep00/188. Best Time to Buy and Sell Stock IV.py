class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        memo = {}
        
        def dp(i, transaction, price_sign):
            if transaction == 0 or i == len(prices):
                return 0
            if (i, transaction, price_sign) in memo:
                return memo[(i, transaction, price_sign)]
            
            memo[(i, transaction, price_sign)] = max(
                dp(i+1, transaction, price_sign), 
                (price_sign * prices[i])
                + dp(i+1, transaction-1 if price_sign > 0 else transaction, price_sign * -1)
            )
            
            return memo[(i, transaction, price_sign)]
        
        return dp(0, k, -1)
