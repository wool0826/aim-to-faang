import java.util.Collections.min
import java.util.PriorityQueue

class Solution815 {
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if (source == target) {
            return 0
        }

        // 이진탐색을 위한 정렬처리
        // N * M * log(M)
        routes.forEach { it.sort() }

        val reachable = Array(MAX_ELEMENT) { BooleanArray(MAX_ELEMENT) { false } }

        // source, target 이 route 내에 존재하는 bus 를 저장하여 최단거리탐색에서 활용
        val startBuses = mutableSetOf<Int>()
        val endBuses = mutableSetOf<Int>()

        // N
        for (i in routes.indices) {
            // log(M)
            if (binarySearch(source, routes[i])) startBuses.add(i)
            if (binarySearch(target, routes[i])) endBuses.add(i)

            // N
            for (j in (i + 1)..routes.lastIndex) {
                // M * log(M)
                for (busStop in routes[i]) {
                    if (binarySearch(busStop, routes[j])) {
                        // i번 버스와 j번 버스는 서로 인접해있다.
                        reachable[i][j] = true
                        reachable[j][i] = true
                    }
                }
            }
        }

        // 가능한 시작버스들을 기준으로 모든 최단거리를 계산한 뒤, 계산된 최단거리 중 최소값을 반환
        val minimumDistance =
            min(startBuses.map { startBus -> calculateMinimumDistance(startBus, endBuses.toIntArray(), reachable) })

        return if (minimumDistance == Int.MAX_VALUE) -1 else minimumDistance
    }

    private fun binarySearch(target: Int, fromArray: IntArray): Boolean {
        return fromArray.binarySearch(target) >= 0
    }

    // 다익스트라 알고리즘을 이용하여, 최단거리를 구해낸다.
    private fun calculateMinimumDistance(
        startBus: Int,
        endBuses: IntArray,
        reachable: Array<BooleanArray>
    ): Int {
        if (endBuses.isEmpty()) {
            return Int.MAX_VALUE
        }

        val visitedYn = BooleanArray(MAX_ELEMENT) { false }
        val distance = IntArray(MAX_ELEMENT) { Int.MAX_VALUE }

        val priorityQueue = PriorityQueue<Pair<Int, Int>> { a, b ->
            if (a.first != b.first) {
                a.first.compareTo(b.first)
            } else {
                a.second.compareTo(b.second)
            }
        }

        distance[startBus] = 1
        priorityQueue.add(Pair(0, startBus))

        while (priorityQueue.isNotEmpty()) {
            var current: Pair<Int, Int>
            do {
                current = priorityQueue.poll()
            } while (priorityQueue.isNotEmpty() && visitedYn[current.second])

            if (binarySearch(current.second, endBuses) || visitedYn[current.second]) {
                break
            }
            visitedYn[current.second] = true

            for ((location, reachableYn) in reachable[current.second].withIndex()) {
                if (!reachableYn || visitedYn[location]) continue

                if (distance[location] > distance[current.second] + 1) {
                    distance[location] = distance[current.second] + 1
                    priorityQueue.add(Pair(distance[location], location))
                }
            }
        }

        return min(endBuses.map { distance[it] })
    }

    companion object {
        private const val MAX_ELEMENT = 500
    }
}

fun main() {
    println(Solution815().numBusesToDestination(arrayOf(intArrayOf(1,2,7), intArrayOf(3,6,7)), 1, 6)) // 2
    println(Solution815().numBusesToDestination(arrayOf(intArrayOf(12,7), intArrayOf(4,15,5), intArrayOf(6), intArrayOf(15, 19), intArrayOf(9,12,13)), 15, 12)) // -1
    println(Solution815().numBusesToDestination(arrayOf(intArrayOf(12,7,4), intArrayOf(4,15,5), intArrayOf(6), intArrayOf(15, 19), intArrayOf(9,12,13)), 15, 12)) // 2
}