
class Solution41 {
    fun firstMissingPositive(nums: IntArray): Int {
        val existArray = BooleanArray(MAX_ELEMENT + 1) { false }

        // 1 pass - check
        for (num in nums) { // N
            if (num <= 0 || num >= MAX_ELEMENT) continue
            existArray[num] = true
        }

        // 2 pass - find missing positive
        for (i in 1..existArray.lastIndex) { // N
            if (!existArray[i]) {
                return i
            }
        }

        return MAX_ELEMENT
    }

    companion object {
        const val MAX_ELEMENT = 500_001
    }
}

fun main() {
    println(Solution41().firstMissingPositive(intArrayOf(1,2,0)))
    println(Solution41().firstMissingPositive(intArrayOf(3,4,-1,1)))
    println(Solution41().firstMissingPositive(intArrayOf(-1)))
    println(Solution41().firstMissingPositive(intArrayOf(7,8,9,11,12)))
    println(Solution41().firstMissingPositive(intArrayOf(4,2,3,1)))
}