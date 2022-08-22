import kotlin.math.max
import kotlin.math.min

class HouseRobber {
    fun rob(nums: IntArray): Int {
        if (nums.size <= 1) {
            return nums[0]
        }

        val dp = IntArray(nums.size) { 0 }

        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for (i in 2 until nums.size) {
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])
        }

        return dp.last()
    }
}

class MinCostClimbingStairs {
    fun minCostClimbingStairs(cost: IntArray): Int {
        val dp = IntArray(cost.size) { 0 }

        dp[0] = cost[0]
        dp[1] = cost[1]

        for (i in 2 until cost.size) {
            dp[i] = min(dp[i-1], dp[i-2]) + cost[i]
        }

        return min(dp[cost.lastIndex], dp[cost.lastIndex - 1])
    }
}

class NthTribonacciNumber {
    fun tribonacci(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        val dp = IntArray(n + 1)

        dp[0] = 0
        dp[1] = 1
        dp[2] = 1

        for (i in 3..n) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        }

        return dp[n]
    }
}

class DeleteAnEarn {
    fun deleteAndEarn(nums: IntArray): Int {
        val frequency = IntArray(MAX_SIZE) { 0 }
        var maxNumber = 0

        for (num in nums) {
            frequency[num]++
            maxNumber = max(maxNumber, num)
        }

        if (maxNumber < 2) {
            return frequency[1]
        }

        val dp = IntArray(maxNumber + 1) { 0 }

        dp[1] = frequency[1]
        dp[2] = max(dp[1], frequency[2] * 2)

        for (i in 3 ..maxNumber) {
            dp[i] = max(dp[i-1], dp[i-2] + frequency[i] * i)
        }

        return dp[maxNumber]
    }

    companion object {
        const val MAX_SIZE = 10001
    }
}

class MaximumScoreFromPerformingMultiplicationOperations {
    fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
        TODO("어렵다..!!!")
    }
}

fun main() {
    println("====HOUSE ROBBER====")
    println(HouseRobber().rob(intArrayOf(2,1,1,2)))
    println(HouseRobber().rob(intArrayOf(2,7,9,3,1)))

    println("====MIN COST CLIMBING STAIRS====")
    println(MinCostClimbingStairs().minCostClimbingStairs(intArrayOf(10,15,20)))
    println(MinCostClimbingStairs().minCostClimbingStairs(intArrayOf(1,100,1,1,1,100,1,1,100,1)))

    println("====Nth Tribonacci Number====")
    println(NthTribonacciNumber().tribonacci(0))
    println(NthTribonacciNumber().tribonacci(1))
    println(NthTribonacciNumber().tribonacci(2))
    println(NthTribonacciNumber().tribonacci(3))
    println(NthTribonacciNumber().tribonacci(4))
    println(NthTribonacciNumber().tribonacci(25))

    println("====Delete And Earn====")
    println(DeleteAnEarn().deleteAndEarn(intArrayOf(3,4,2)))
    println(DeleteAnEarn().deleteAndEarn(intArrayOf(2,2,3,3,3,4)))
    println(DeleteAnEarn().deleteAndEarn(intArrayOf(1,1,1,1,1,1,1,1,2,3,5)))
}