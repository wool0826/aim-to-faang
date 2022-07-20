import java.util.Collections.binarySearch
import kotlin.math.max

class Solution1235 {
    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val jobs = mutableListOf<Job>()
        val lastIndex = startTime.lastIndex

        // 각 요소들을 묶어서 정렬해주기 위해
        for (index in 0 .. lastIndex) {
            jobs.add(Job(startTime[index], endTime[index], profit[index]))
        }

        // endTime 으로 뽑으면 쉬워서 이걸로 정렬
        jobs.sortBy { it.endTime }

        val maxProfit = IntArray(profit.size + 1) { 0 }

        // dp[i] = MAX( dp[i-1], dp[X] + profit[i] )
        // dp[X].endTime 이 job[i].startTime 보다 작은 가장 가까운 인덱스
        for (i in 1 .. lastIndex + 1) {
            maxProfit[i] = max(maxProfit[i-1], maxProfit[calculateLastIndex(jobs, i-1)] + jobs[i-1].profit)
        }

        return maxProfit[lastIndex + 1]
    }

    private fun calculateLastIndex(jobs: List<Job>, jobIndex: Int): Int {
        val currentJobStartTime = jobs[jobIndex].startTime

        var left = 0
        var right = jobIndex + 1

        while (left < right) {
            val mid = (left + right) / 2

            if (jobs[mid].endTime < currentJobStartTime) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        while (left <= jobs.lastIndex && jobs[left].endTime <= currentJobStartTime) left++

        return left
    }
}

data class Job(
    val startTime: Int,
    val endTime: Int,
    val profit: Int
)

fun main() {
    println(
        Solution1235().jobScheduling(
            startTime = intArrayOf(1,2,3,3),
            endTime = intArrayOf(3,4,5,6),
            profit = intArrayOf(50,10,40,70)
        )
    )

    println(
        Solution1235().jobScheduling(
            startTime = intArrayOf(1,2,3,4,6),
            endTime = intArrayOf(3,5,10,6,9),
            profit = intArrayOf(20,20,100,70,60)
        )
    )

    println(
        Solution1235().jobScheduling(
            startTime = intArrayOf(1,1,1),
            endTime = intArrayOf(2,3,4),
            profit = intArrayOf(5,6,4)
        )
    )
}