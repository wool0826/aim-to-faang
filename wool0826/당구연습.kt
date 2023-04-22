class SolutionBilliards {
    fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        return balls
            .map { ball -> calculate(m, n, startX, startY, ball).minOf { it }}
            .toIntArray()
    }

    private fun calculate(m: Int, n: Int, startX: Int, startY: Int, ball: IntArray): List<Int> {
        val results = mutableListOf<Int>()

        // 좌측 y축
        if (ball[1] != startY || ball[0] > startX) {
            results.add((-1 * ball[0] - startX).square() + (ball[1] - startY).square())
        }

        // 우측 y축
        if (ball[1] != startY || ball[0] < startX) {
            results.add((2 * m - ball[0] - startX).square() + (ball[1] - startY).square())
        }

        // 위쪽 x축
        if (ball[0] != startX || ball[1] < startY) {
            results.add((ball[0] - startX).square() + (2 * n - ball[1] - startY).square())
        }

        // 아래쪽 x축
        if (ball[0] != startX || ball[1] > startY) {
            results.add((ball[0] - startX).square() + (-1 * ball[1] - startY).square())
        }

        return results
    }

    private fun Int.square(): Int {
        return this * this
    }
}

fun main(args: Array<String>) {
    SolutionBilliards().solution(10, 10, 3, 7, arrayOf(intArrayOf(7, 7), intArrayOf(2, 7), intArrayOf(7 ,3))).forEach { println(it) }
}
