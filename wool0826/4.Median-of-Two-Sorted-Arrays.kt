fun main() {
    val solution = Solution()
    println(solution.findMedianSortedArrays(intArrayOf(1,2,3,4,5,6), intArrayOf(7,4,2,32,6,1,1)))
}

class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val sortedArray = (nums1 + nums2).sortedArray()
        val mid = sortedArray.size / 2

        // 좀 더... 좋은 알고리즘 고민해보자...
        return if (sortedArray.size % 2 == 0) {
            (sortedArray[mid-1] + sortedArray[mid]) / 2.0
        } else {
            sortedArray[mid].toDouble()
        }
    }
}