class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        first_node = ListNode()
        last_node = first_node
        
        for l1 in lists:
            l2 = first_node.next
            last_node = first_node
            
            while l1 != None and l2 != None:
                next_node = None
                if l1.val < l2.val:
                    next_node = l1
                    l1 = l1.next
                else:
                    next_node = l2
                    l2 = l2.next
                last_node.next = next_node
                    
                last_node = last_node.next
            
            if l1 != None:
                last_node.next = l1
            else:
                last_node.next = l2

        return first_node.next
