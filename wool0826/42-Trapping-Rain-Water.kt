import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val solution = Solution()

    println(solution.trap(arrayOf(0,1,0,2,1,0,1,3,2,1,2,1).toIntArray()))
}

class Solution {
    fun trap(height: IntArray): Int {
        val left = IntArray(height.size) { 0 }
        val right = IntArray(height.size) { 0 }

        left[0] = height.first()
        right[height.lastIndex] = height.last()

        for (i in 1..height.lastIndex) {
            left[i] = max(left[i - 1], height[i])
            right[height.lastIndex - i] = max(right[height.lastIndex - i + 1], height[height.lastIndex - i])
        }

        return height.indices.sumOf { index -> min(left[index], right[index]) - height[index] }
    }
}