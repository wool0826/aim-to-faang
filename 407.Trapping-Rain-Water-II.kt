import kotlin.math.max
import kotlin.math.min

class Solution407 {
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val yAxis = heightMap.size
        val xAxis = heightMap[0].size

        val trappedWaterHeights = Array(yAxis) { IntArray(xAxis) { 0 } }

        // 일단 y 축을 기준으로 최대높이를 정해줌
        for (y in 0 until yAxis) {
            val left = IntArray(xAxis) { 0 }
            val right = IntArray(xAxis) { 0 }

            for (x in 0  until xAxis) {
                left[x] = max(left[(x - 1).coerceAtLeast(0)], heightMap[y][x])
                right[xAxis - x - 1] = max(right[(xAxis - x).coerceAtMost(xAxis - 1)], heightMap[y][xAxis - x - 1])
            }

            for (x in 0 until xAxis) {
                trappedWaterHeights[y][x] = min(left[x], right[x])
            }
        }

        // x 축을 기준으로 다시 계산해줌.
        for (x in 0 until xAxis) {
            val left = IntArray(yAxis) { 0 }
            val right = IntArray(yAxis) { 0 }

            for (y in 0 until yAxis) {
                left[y] = max(left[(y - 1).coerceAtLeast(0)], heightMap[y][x])
                right[yAxis - y - 1] = max(right[(yAxis - y).coerceAtMost(yAxis - 1)], heightMap[yAxis - y - 1][x])
            }

            for (y in 0 until yAxis) {
                trappedWaterHeights[y][x] = min(trappedWaterHeights[y][x], left[y], right[y])
            }
        }

        var result = 0

        for (x in 0 until xAxis) {
            for (y in 0 until yAxis) {
                result += trappedWaterHeights[y][x] - heightMap[y][x]
            }
        }

        return result
    }

    private fun min(a: Int, b: Int, c: Int): Int {
        return min(min(a, b),c)
    }
}

fun main() {
    // 4
    println(
        Solution407().trapRainWater(
            arrayOf(
                intArrayOf(1,4,3,1,3,2),
                intArrayOf(3,2,1,3,2,4),
                intArrayOf(2,3,3,2,3,1)
            )
        )
    )

    // 2
    println(
        Solution407().trapRainWater(
            arrayOf(
                intArrayOf(3,3,3,3,3,3),
                intArrayOf(3,3,3,1,3,3),
                intArrayOf(3,3,3,3,3,3)
            )
        )
    )

    // 0
    println(
        Solution407().trapRainWater(
            arrayOf(
                intArrayOf(3,3,3,1,3,3),
                intArrayOf(3,3,3,1,3,3),
                intArrayOf(3,3,3,1,3,3)
            )
        )
    )

    // 0
    println(
        Solution407().trapRainWater(
            arrayOf(
                intArrayOf(5,4,3,2,1),
                intArrayOf(5,4,3,2,1),
                intArrayOf(5,4,3,2,1)
            )
        )
    )

    // 0
    println(
        Solution407().trapRainWater(
            arrayOf(
                intArrayOf(5,4,3,2,1),
                intArrayOf(1,2,3,4,5),
                intArrayOf(5,4,3,2,1)
            )
        )
    )

    println(
        Solution407().trapRainWater(
            arrayOf(
                intArrayOf(12,13,1,12),
                intArrayOf(13,4,13,12), // 13
                intArrayOf(13,8,10,12), // 12, 12
                intArrayOf(12,13,12,12),
                intArrayOf(13,13,13,13),
            )
        )
    )
}