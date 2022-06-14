import java.util.PriorityQueue

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty() || lists.isAllNodesEmpty()) {
            return null
        }

        // 우선순위 큐에 ListNode 를 넣어두면, 가장 value 가 작은 것만 poll 할 수 있음.
        val priorityQueue = PriorityQueue<Pair<Int, ListNode?>> { a, b -> a.first.compareTo(b.first) }

        // N: Lists 내의 총 원소 수
        // N * log(N)
        priorityQueue.addAll(
            lists.filterNotNull().map { Pair(it.`val`, it) }
        )

        val head = ListNode(-1)
        var currentNode: ListNode = head

        // N
        while (priorityQueue.isNotEmpty()) {
            val minimumValueNode = priorityQueue.poll()
            val nextNode = ListNode(minimumValueNode.first)

            currentNode.next = nextNode
            currentNode = nextNode

            val availableNode = minimumValueNode.second?.next
            if (availableNode != null) {
                // log(N)
                priorityQueue.add(Pair(availableNode.`val`, availableNode))
            }
        }

        return head.next
    }

    private fun Array<ListNode?>.isAllNodesEmpty(): Boolean {
        return this.all { it == null }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun main() {
    val node1 =
        Solution23().mergeKLists(
            arrayOf(
                buildListNode(arrayOf(1,4,5)),
                buildListNode(arrayOf(1,3,4))
            )
        )

    traversalNode(node1)
}

// 테스트 데이터를 위해서 만든 ListNode 생성 메소드
private fun buildListNode(lists: Array<Int>): ListNode? {
    if (lists.isEmpty()) {
        return null
    }

    val head = ListNode(lists.first())
    var current = head

    for (i in 1 .. lists.lastIndex) {
        val nextNode = ListNode(lists[i])

        current.next = nextNode
        current = nextNode
    }

    return head
}

// 테스트 데이터를 위해서 만든 ListNode 순회 메소드
private fun traversalNode(head: ListNode?) {
    if (head == null) {
        return
    }

    var current: ListNode? = head

    while (current != null) {
        print("${current.`val`} -> ")
        current = current.next
    }

    println()
}