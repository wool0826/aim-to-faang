import java.util.PriorityQueue
import kotlin.math.absoluteValue

class SolutionLand {
    private val moveX = intArrayOf(0,0,1,-1)
    private val moveY = intArrayOf(1,-1,0,0)

    /**
     * ex.
     *
     * ## land 배열
     *
     *  1  4  8  10
     *  5  5  5  5
     * 10 10 10 10
     * 10 10 10 20
     *
     * ## minimumCost 배열 결과
     *
     *   0 INF INF INF
     * INF INF INF INF
     *   5 INF INF INF
     * INF INF INF  10
     *
     * minimumCost 의 총합 = 15
     */
    fun solution(land: Array<IntArray>, height: Int): Int {
        // ladderCost 가 가장 낮은 것부터 돌도록 처리
        val pq = PriorityQueue { p1: Point, p2: Point ->  p1.cost.compareTo(p2.cost) }

        val minimumCost = Array(land.size) { IntArray(land.size) { Int.MAX_VALUE } }
        val visited = Array(land.size) { BooleanArray(land.size) { false } }

        pq.add(Point(x = 0, y = 0, cost = 0))
        minimumCost[0][0] = 0

        while (pq.isNotEmpty()) {
            var curr: Point

            do {
                curr = pq.remove()
            } while (pq.isNotEmpty() && visited[curr.y][curr.x])

            if (visited[curr.y][curr.x]) {
                break
            }

            visited[curr.y][curr.x] = true

            if (curr.cost != 0) {
                // ladderCost 가 존재하는 경우, 해당 지점에 ladderCost 를 저장
                // minimumCost[nextY][nextX] 에는 해당 지점으로 도달할 수 있는 최소 ladderCost 를 저장하게 됨.
                minimumCost[curr.y][curr.x] = kotlin.math.min(minimumCost[curr.y][curr.x], curr.cost)
            }

            for (i in moveX.indices) {
                val nextX = curr.x + moveX[i]
                val nextY = curr.y + moveY[i]

                if (isInBoundary(nextX, nextY, land.size)) {
                    val ladderCost = (land[nextY][nextX] - land[curr.y][curr.x]).absoluteValue.let {
                        if (it > height) { it } else { 0 }
                    }

                    pq.add(Point(nextX, nextY, ladderCost))
                }
            }
        }

        return minimumCost
            .flatMap { it.asIterable() }
            .filter { it != Int.MAX_VALUE }
            .sum()
    }

    private fun isInBoundary(x: Int, y: Int, maxExclusive: Int): Boolean {
        return x in (0 until maxExclusive) && y in (0 until maxExclusive)
    }
}

data class Point(val x: Int, val y: Int, val cost: Int)

fun main() {
    println(
        SolutionLand().solution(
            arrayOf(
                intArrayOf(1,4,8,10),
                intArrayOf(6,5,9,9),
                intArrayOf(10,10,13,13),
                intArrayOf(10,10,15,20),
            ),
            3
        )
    )

    println(
        SolutionLand().solution(
            arrayOf(
                intArrayOf(1,4,8,10),
                intArrayOf(5,5,5,5),
                intArrayOf(10,10,10,10),
                intArrayOf(10,10,10,20),
            ),
            3
        )
    )

    println(
        SolutionLand().solution(
            arrayOf(
                intArrayOf(10,11,10,11),
                intArrayOf(2,21,20,10),
                intArrayOf(1,20,21,11),
                intArrayOf(2,1,2,1),
            ),
            1
        )
    )
}
