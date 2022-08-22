class Solution1293 {
    private val moveDirections = listOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

    fun shortestPath(grid: Array<IntArray>, k: Int): Int {
        val maxWidth = grid[0].size
        val maxHeight = grid.size

        // 해당 y, x 좌표 지점에 k번 장애물을 부수면서 도착한 적이 있는지 기록
        val visited = Array(maxHeight) { Array(maxWidth) { BooleanArray(k + 1) { false } } }

        // Triple( y좌표, x좌표, 장애물 부순 횟수 )
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        queue.add(Triple(0, 0, 0))

        var stepCount = 0

        // BFS 이동횟수를 측정하기 위해 queue 에 있는 사이즈만큼만 반복
        while (queue.isNotEmpty()) {
            val queueSize = queue.size

            for (i in 0 until queueSize) {
                val currentStep = queue.removeFirst()

                // 방문했던 지점이면 이후 로직 수행하지 않도록 continue
                if (visited[currentStep.first][currentStep.second][currentStep.third]) {
                    continue
                }

                // 도착지점이면 그만 수행
                if (currentStep.first == maxHeight - 1 && currentStep.second == maxWidth - 1) {
                    return stepCount
                }

                // 방문 기록
                visited[currentStep.first][currentStep.second][currentStep.third] = true

                // 4방향으로 움직일 수 있으므로, 이동할 수 있는 지점을 모두 탐색해 봄.
                for (nextMoveDirection in moveDirections) {
                    val nextY = currentStep.first + nextMoveDirection.first
                    val nextX = currentStep.second + nextMoveDirection.second

                    // 경계 내에 있는 경우
                    if (checkBoundary(nextY, nextX, maxHeight, maxWidth)) {
                        // 장애물을 부숴야하는지, 부숴야하면 K번 부숴야하는 조건을 만족하는지 확인
                        val nextBreakCount = grid[nextY][nextX] + currentStep.third

                        if (nextBreakCount <= k && !visited[nextY][nextX][nextBreakCount]) {
                            // 만족한다면 다음 탐색지점으로 queue에 넣음
                            queue.add(Triple(nextY, nextX, nextBreakCount))
                        }
                    }
                }
            }

            stepCount++
        }

        return -1
    }

    // y, x 가 지정된 배열의 범위 내에 있는 값인지 확인하는 함수
    private fun checkBoundary(y: Int, x: Int, maxHeight: Int, maxWidth: Int): Boolean {
        return (y in 0 until maxHeight) && (x in 0 until maxWidth)
    }
}

fun main() {
    println(Solution1293().shortestPath(
        arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(1,1,0),
            intArrayOf(0,0,0),
            intArrayOf(0,1,1),
            intArrayOf(0,0,0)
        ),
        1
    ))

    println(Solution1293().shortestPath(
        arrayOf(
            intArrayOf(0,1,1),
            intArrayOf(1,1,1),
            intArrayOf(1,0,0)
        ),
        1
    ))

    println(Solution1293().shortestPath(
        arrayOf(
            intArrayOf(0,1,1),
            intArrayOf(1,1,1),
            intArrayOf(1,0,0)
        ),
        2
    ))
}