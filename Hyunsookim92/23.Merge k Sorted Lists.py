# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        length = len(lists)
        tmpLists = list()
        solLists = ListNode()

        for i in range(length):
            node = lists[i]
            while node.next:
                tmpLists.append(node.val)
                node = node.next
            tmpLists.append(node.val)

        tmpLists = sorted(tmpLists)

        print(tmpLists)

        curLists = ListNode(tmpLists[0])
        nextLists = ListNode(tmpLists[1])

        solLists = ListNode(curLists.val, nextLists)
        print(solLists)

        curLists = solLists
        print(curLists)

        nextLists = ListNode(tmpLists[2])
        solLists = ListNode(curLists, nextLists)
        print(solLists)