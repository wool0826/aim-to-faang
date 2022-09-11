import kotlin.math.max
import kotlin.math.min

class Solution1335 {
    lateinit var dp: Array<IntArray>

    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        if (jobDifficulty.size < d) { // 스케쥴링 할 수 없는 상황을 먼저 배제
            return -1
        }

        dp = Array(d + 1) { IntArray(jobDifficulty.size + 1) { MAX_VALUE } }
        return process(jobDifficulty, remainDay = d, mustBeDoneOnTodayJobIndex = 0)

    }

    // 현재까지 며칠 남았는지, 오늘은 어떤 작업을 해야하는지 파라미터로 넘겨준다.
    fun process(jobDifficulty: IntArray, remainDay: Int, mustBeDoneOnTodayJobIndex: Int): Int {
        // 아직 모든 날을 처리되지 않았는데 job 은 모두 스케쥴링된 경우, 잘못한 것이므로 쓰레기값을 반환한다.
        if (remainDay > 0 && mustBeDoneOnTodayJobIndex > jobDifficulty.lastIndex) return MAX_VALUE

        // 오늘이 마지막 날이면, 남은 작업들을 모두 처리해야한다.
        // 남은 작업들 중 difficulty 가 가장 높은 job 의 값을 반환한다.
        if (remainDay == 1) return maxOfSubArray(jobDifficulty, mustBeDoneOnTodayJobIndex, jobDifficulty.lastIndex)

        // 메모이제이션한 값이 있으면 바로 반환해서 최적화한다.
        if (dp[remainDay][mustBeDoneOnTodayJobIndex] != MAX_VALUE) {
            return dp[remainDay][mustBeDoneOnTodayJobIndex]
        }

        // 오늘 1~N개의 업무를 처리할 건데, 그 업무들의 max difficulty 를 저장한다.
        var todayDifficulty = 0

        for (i in mustBeDoneOnTodayJobIndex..jobDifficulty.lastIndex) {
            todayDifficulty = max(todayDifficulty, jobDifficulty[i])

            // 오늘 mustBeDoneOnTodayJobIndex 를 처리하였을 때 나올 수 있는 최소 MaxDifficulty 를 계산하여 저장한다.
            dp[remainDay][mustBeDoneOnTodayJobIndex] =
                min(
                    dp[remainDay][mustBeDoneOnTodayJobIndex],
                    todayDifficulty +  process(jobDifficulty, remainDay - 1, i + 1)
                )
        }

        return dp[remainDay][mustBeDoneOnTodayJobIndex]
    }

    private fun maxOfSubArray(array: IntArray, from: Int, to: Int): Int {
        if (from > to) return -1

        var maxValue = 0

        for (i in from..to) {
            maxValue = max(maxValue, array[i])
        }

        return maxValue
    }

    companion object {
        const val MAX_VALUE = 300001
    }
}

fun main() {
    println(Solution1335().minDifficulty(intArrayOf(6,5,4,3,2,1), 2))
    println(Solution1335().minDifficulty(intArrayOf(1,1,1), 3))
}