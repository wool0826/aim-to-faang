class Solution:    
    def trap(self, height: List[int]) -> int:
        
        s = 0
        temp_l = 0
        max_l = 0
        
        check_l_index = 0
        
        temp_r = 0
        max_r = 0
        
        # l
        for i in range(len(height)):
            if max_l <= height[i]:
                s += temp_l
                temp_l = 0
                check_l_index = i
            max_l = max(height[i], max_l)
            temp_l += max(max_l - height[i], 0)
            
        # r
        for i in range(len(height)-1, check_l_index-1, -1):
            if max_r <= height[i]:
                s += temp_r
                temp_r = 0
            max_r = max(height[i], max_r)
            temp_r += max(max_r - height[i], 0)
                
        return s

sol = Solution()

print(sol.trap([0,1,0,2,1,0,1,3,2,1,2,1]))
