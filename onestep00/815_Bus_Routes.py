class Solution:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:
        if source == target:
            return 0
        
        from collections import deque
        
        stop = dict()
        bus = dict()
        
        for b, stop_list in enumerate(routes):
            for s in stop_list:
                # print(b, s)
                if s not in stop:
                    stop[s] = set()
                stop[s].add(b)
                
                if b not in bus:
                    bus[b] = set()
                bus[b].add(s)
           
        check_bus = set()
        queue = deque(stop[source])
        
        queue_size = len(queue)
        i = 0
        count = 1
        
        while queue:
            if i == queue_size:
                i = 0
                count += 1
                queue_size = len(queue)
                
            b = queue.popleft()
            i += 1
            
            if b not in check_bus:
                check_bus.add(b)
                for s in bus[b]:
                    if s == target:
                        return count
                    queue += stop[s] - set(check_bus)
                    
            
        
        return -1
