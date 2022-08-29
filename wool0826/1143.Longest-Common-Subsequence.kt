import kotlin.math.max

class LongestCommonSubsequence {
    lateinit var t1: String
    lateinit var t2: String
    lateinit var dp: Array<IntArray>

    fun longestCommonSubsequence(text1: String, text2: String): Int {
        dp = Array(text1.length) { IntArray(text2.length) { -1 } }
        t1 = text1
        t2 = text2

        return solve(0, 0)
    }

    private fun solve(p1: Int, p2: Int): Int {
        if (p1 >= t1.length || p2 >= t2.length) return 0
        if (dp[p1][p2] != -1) return dp[p1][p2]

        dp[p1][p2] =
            if (t1[p1] == t2[p2]) 1 + solve(p1 + 1 , p2 + 1)
            else max(solve(p1 + 1, p2), solve(p1, p2 + 1))

        return dp[p1][p2]
    }
}

fun main() {
    println(LongestCommonSubsequence().longestCommonSubsequence("abcd", "abd"))
    println(LongestCommonSubsequence().longestCommonSubsequence("abcde", "ace"))
}