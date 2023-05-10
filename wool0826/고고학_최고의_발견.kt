import kotlin.math.min

class SolutionGogo {
    private val candidateX = intArrayOf(0, 0, 0, -1, 1)
    private val candidateY = intArrayOf(0, 1, -1, 0, 0)
    private var minimumValue = Int.MAX_VALUE

    fun solution(clockHands: Array<IntArray>): Int {
        val n = clockHands.size
        traversal(0, n, IntArray(size = n), clockHands)

        return minimumValue
    }

    fun traversal(i: Int, n: Int, initialArray: IntArray, clockHands: Array<IntArray>) {
        if (i >= n) {
            // initialArray => (0,0,0,0) ~ (3,3,3,3)
            solve(initialArray, clockHands, n)
            return
        }

        for (value in 0 until 4) {
            initialArray[i] = value
            traversal(i + 1, n, initialArray, clockHands)
        }
    }

    private fun solve(initialArray: IntArray, clockHands: Array<IntArray>, n: Int) {
        val temp = Array(n) { IntArray(n) }
        var count = 0

        for (y in 0 until n) {
            for (x in 0 until n) {
                temp[y][x] = clockHands[y][x]
            }
        }

        for ((x, rotateCount) in initialArray.withIndex()) {
            if (rotateCount == 0) {
                continue
            }

            rotate(temp, 0, x, n, rotateCount)
            count += rotateCount
        }

        for (y in 1 until n) {
            for (x in 0 until n) {
                // temp[y][x]
                // temp[y-1][x] <= 4 가 될 수 있게 돌린다.

                if (temp[y - 1][x] == 0) {
                    continue
                }

                val c = 4 - temp[y - 1][x]
                rotate(temp, y, x, n, c)

                count += c
            }
        }

        if (temp[n - 1].sum() == 0) {
            minimumValue = min(minimumValue, count)
        }
    }

    fun rotate(clockHandsCopy: Array<IntArray>, y: Int, x: Int, n: Int, rotateCount: Int) {
        for (i in 0 until 5) {
            val nextY = y + candidateY[i]
            val nextX = x + candidateX[i]

            if (nextY in (0 until n) && nextX in (0 until n)) {
                clockHandsCopy[nextY][nextX] = (clockHandsCopy[nextY][nextX] + rotateCount) % 4
            }
        }
    }
}

fun Array<IntArray>.toString2() {
    this.forEach { a ->
        a.forEach { print("$it, ") }
        println()
    }
    println()
}

fun main() {
    println(
        SolutionGogo().solution(
            arrayOf(
                intArrayOf(0, 3, 3, 0),
                intArrayOf(3, 2, 2, 3),
                intArrayOf(0, 3, 2, 0),
                intArrayOf(0, 3, 3, 3),
            ),
        ),
    )
}
