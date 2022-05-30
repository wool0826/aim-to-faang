package ex

import java.util.Deque

class Algorithms {
    fun kmp(base: String, patternToFind: String) {
        val pi = IntArray(base.length)

        // 1. build pi array
        var j = 0
        for (i in 1 until base.length) {
            while (j > 0 && base[i] != base[j])
                j = pi[j-1] // 이전 문자열까지 일치했던 prefix 위치 + 1 로 이동하여 계속 비교

            if (base[i] == base[j])
                pi[i] = ++j
        }

        // 2. find pattern in base string
        j = 0
        for (i in base.indices) {
            while (j > 0 && base[i] != patternToFind[j])
                j = pi[j-1] // 이전 문자열까지 일치했던 prefix 위치 + 1 로 이동하여 계속 비교

            if (base[i] == patternToFind[j]) {
                if (j == patternToFind.lastIndex) {
                    println("match! ${i - patternToFind.length + 1}")
                    j = pi[j]
                } else {
                    j++
                }
            }
        }
    }

    fun bfsWithoutDistance() {
        val size = 10

        val move = arrayOf(Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1,0))
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = Array(size) { BooleanArray(size) { false } }

        queue.add(Pair(0,0)) // start 넣고 시작.
        visited[0][0] = true

        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            visited[next.second][next.first] = true

            if (next.second == size && next.first == size) {
                return // end 에 도착하면 종료하는 머 그런 느낌
            }

            for (nextMove in move) {
                val nextY = next.second + nextMove.second
                val nextX = next.first + nextMove.first


                if (!visited[nextY][nextX]) {
                    queue.add(Pair(nextY, nextX))
                }
            }
        }
    }

    fun bfsWithDistance() {
        val size = 10

        val move = arrayOf(Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1,0))
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = Array(size) { BooleanArray(size) { false } }

        queue.add(Pair(0,0)) // start 넣고 시작.
        visited[0][0] = true

        var distance = 0

        while (queue.isNotEmpty()) {
            for (i in 0 until queue.size) {
                val next = queue.removeFirst()
                visited[next.second][next.first] = true

                // if (pos == end) { return distance }

                for (nextMove in move) {
                    val nextY = next.second + nextMove.second
                    val nextX = next.first + nextMove.first

                    if (!visited[nextY][nextX]) {
                        queue.add(Pair(nextY, nextX))
                    }
                }
            }

            distance++
        }
    }
}