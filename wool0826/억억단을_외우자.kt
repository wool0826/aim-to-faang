private const val MAX = 5_000_001

class SolutionMultiplier {
    // 각 숫자의 약수 개수를 계산해서 저장
    private val divisorCount = IntArray(MAX) { 1 }

    init {
        // 에라토스테네스의 체 변형
        val maxValue = kotlin.math.sqrt(MAX.toDouble()).toInt()

        for (i in 2..maxValue) {
            // ex) 12 의 경우, 2^2 * 3^1 으로 표현할 수 있다.
            // 즉, 약수의 개수는 (2+1) * (1+1) = 6
            if (divisorCount[i] == 1) {
                // 소수는 추가로 계산하기 귀찮으니 1 더해준다.
                divisorCount[i] += 1

                for (j in i + 1 until MAX) {
                    if (j % i == 0) {
                        var count = 0
                        var num = j

                        while (num % i == 0) {
                            num /= i
                            count++
                        }

                        divisorCount[j] *= (count + 1)
                    }
                }
            }
        }
    }

    fun solution(e: Int, starts: IntArray): IntArray {
        var maxIndex = 0
        var lastIndex = e

        val indexMap = mutableMapOf<Int, Int>()

        // 중복계산을 피하기 위해서, starts 가 가장 큰 값부터 계산
        for (startIndex in starts.sortedDescending()) {
            if (indexMap[startIndex] != null) {
                continue
            }

            for (j in lastIndex downTo startIndex) {
                if (divisorCount[maxIndex] <= divisorCount[j]) {
                    maxIndex = j
                }
            }

            indexMap[startIndex] = maxIndex
            lastIndex = startIndex
        }

        return starts.map { indexMap[it] }.filterNotNull().toIntArray()
    }
}

fun main() {
    val t = SolutionMultiplier()

    t.solution(8, intArrayOf(1,3,7)).forEach { println(it) }
    println()
    t.solution(10, intArrayOf(1,2,9,1,2,3,5,8)).forEach { println(it) }
}
