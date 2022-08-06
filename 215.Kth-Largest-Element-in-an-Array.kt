import java.util.PriorityQueue

class Solution215 {
    // Priority Queue 를 이용해서 Queue 내에 가장 큰 값 K 개만 남겨두게 하는 방식
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val frequencyQueue = PriorityQueue<Int> { e1, e2 -> e1 - e2 }

        for (num in nums) {
            frequencyQueue.add(num)

            if (frequencyQueue.size > k) {
                frequencyQueue.poll() // 가장 작은 값이 Queue 에서 제거됨.
            }
        }

        return frequencyQueue.poll() // Queue 에 존재하는 K 개의 값 중, 가장 작은 값이 반환됨.
    }

    // Quick Sort 와 유사한 방식의 알고리즘을 사용하여 최대 log(N) 만에 선택하는 방식 (평균 N*log(N) 같음)
    fun findKthLargestWithQuickSelection(nums: IntArray, k: Int): Int {
        return quickSelect(nums, 0, nums.size - 1, nums.size - k)
    }

    private fun quickSelect(nums: IntArray, left: Int, right: Int, smallestK: Int): Int {
        if (left == right) {
            return nums[left]
        }

        // 1. nums 배열에서 left ~ right 범위를 확인
        // 2. 피봇 값 ((left + right) / 2) 보다 작은 값들을 왼쪽으로 몰아넣는데, 정렬같은 거 안 하고 막 집어넣음.
        val (array, pivotIndex) = partition(nums, left, right, (left + right) / 2) // 랜덤하기 귀찮으니까 중간으로 고정

        return if (smallestK == pivotIndex) { // 작은 값들을 다 몰아넣고 그 다음 인덱스가 smallestK 와 동일하면, 이 값이 K 번째로 작은 값이므로 반환
            array[smallestK]
        } else if (smallestK < pivotIndex) { // smallestK 가 index 보다 작으면, 왼쪽 구역에 있는 것이므로 왼쪽구역을 다시 quickSelect
            quickSelect(array, left, pivotIndex - 1, smallestK)
        } else { // 위 조건 반대
            quickSelect(array, pivotIndex + 1, right, smallestK)
        }
    }

    private fun partition(nums: IntArray, left: Int, right: Int, pivotIndex: Int): Pair<IntArray, Int> {
        // cloneNums: [ 3,1,1,5,6,4 ]
        // pivot: 0
        val pivotValue = nums[pivotIndex]
        val cloneNums = nums.clone()

        // cloneNums: [ 4,1,1,5,6,3 ]
        cloneNums.swap(pivotIndex, right)

        var storedIndex = left
        for (i in left .. right) {
            if (cloneNums[i] < pivotValue) { // pivotValue 보다 작으면 왼쪽으로 몰아넣음
                cloneNums.swap(storedIndex, i)
                storedIndex++
            }
        }

        // 여기까지 수행하면 cloneNums: [ 1,1,4,5,6,3 ]
        // storeIndex = 2
        cloneNums.swap(storedIndex, right)


        // 여기까지 수행하면 cloneNums: [ 1,1,3,5,6,4 ]
        // storeIndex = 2
        return Pair(cloneNums, storedIndex)
    }

    private fun IntArray.swap(aIndex: Int, bIndex: Int) {
        val temp = this[bIndex]
        this[bIndex] = this[aIndex]
        this[aIndex] = temp
    }

}

fun main() {
    println(
        Solution215().findKthLargest(
            intArrayOf(3,2,1,5,6,4),
            2
        )
    )

    println(
        Solution215().findKthLargestWithQuickSelection(
            intArrayOf(3,2,3,1,2,4,5,5,6), // 1,2,2,3,3,4,5,5,6
            4
        )
    )

    println(
        Solution215().findKthLargestWithQuickSelection(
            intArrayOf(2,1),
            2
        )
    )
}