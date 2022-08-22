import kotlin.math.max
import kotlin.math.min

class Solution11 {
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.lastIndex

        var maxArea = 0

        while (left < right) {
            val localArea = (right-left) * min(height[left], height[right])
            maxArea = max(maxArea, localArea)

            if (height[left] > height[right]) {
                right--
            } else {
                left++
           }
        }

        return maxArea
    }
}

fun main() {
    println(Solution11().maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
    println(Solution11().maxArea(intArrayOf(1,1,1,15,15,1,1,1,1,1)))
}