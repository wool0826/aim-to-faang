class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        memo = {}
        price_sign = [-1, 1, 0]

        def dp(i, price_sign_index):
            if i == len(prices):
                return 0
            if (i, price_sign_index) in memo:
                return memo[(i, price_sign_index)]

            memo[(i, price_sign_index)] = max(
                dp(i + 1, price_sign_index),
                (price_sign[price_sign_index] * prices[i])
                + dp(
                    i + 1,
                    price_sign_index + 1 if price_sign_index < len(price_sign) - 1 else 0,
                ),
            )

            return memo[(i, price_sign_index)]

        return dp(0, 0)
