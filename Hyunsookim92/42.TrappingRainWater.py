from typing import List

class Solution:
    def trap(self, height: List[int]) -> int:
        cnt = 0     # 가둘 수 있는 물의 양
        for i in range(1,max(height)+1):
            tmp = []
            for j in range(len(height)):
                if height[j] >= i:
                    tmp.append(j)

            if min(tmp) != max(tmp):
                for k in range(min(tmp), max(tmp)):
                    if height[k] < i:
                        cnt += 1
        return cnt

height1 = [0,1,0,2,1,0,1,3,2,1,2,1]
height2 = [2,1,0,2,1,0,1,3,2,1,2,1]
height3 = [4,2,0,3,2,5]
height4 = [5,2,0,3,2,5]

print(Solution().trap(height1))