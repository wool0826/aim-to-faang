import kotlin.math.max
import kotlin.math.min

class MaximalSquare {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size

        val dp = Array(m) { IntArray(n) }

        var maxLength = 0

        for (i in 1 until m) {
            for (j in 1 until n) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = minOf(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1
                }

                maxLength = max(dp[i][j], maxLength)
            }
        }

        return maxLength * maxLength
    }

    private fun minOf(num1: Int, num2: Int, num3: Int): Int {
        return min(min(num1, num2), num3)
    }
}

fun main() {
    println(
        MaximalSquare().maximalSquare(
            arrayOf(
                charArrayOf('1','0','1','0','0'),
                charArrayOf('1','1','1','1','1'),
                charArrayOf('1','1','1','1','1'),
                charArrayOf('1','1','1','1','0')
            )
        )
    )
}