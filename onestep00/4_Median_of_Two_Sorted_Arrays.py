class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        
        # based on shortter array
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
        
        n = len(nums1)
        m = len(nums2)
        
        '''
        split two array by median
              left            |      right
        nums1[0]...nums1[i-1] | nums1[i]...nums1[n-1]
        nums2[0]...nums2[j-1] | nums2[j]...nums2[m-1]
                
        1. left length == right length (+ 1)
            i + j = (n - i) + (m - j) (+ 1)
            j = (n + m) // 2 - i 
              = (n + m + 1) // 2 - i
        
        2. left max <= right min
            nums1[i-1] <= nums2[j]
            nums2[j-1] <= nums1[i]
            
        3. if n + m % 2 == 1
            median is left max
        3.1. if n + m % 2 == 0
            median is mean of left max and right min
        '''
        
        # binary search
        start, end, half = 0, n, (n + m + 1) // 2
        print(start, end, half)
        while start <= end:
            i = (start + end) // 2
            j = half - i
            
            print(start, end, i)
            
            # if nums1[i] is on the left side
            if i < end and nums2[j-1] > nums1[i]:
                start = i + 1
            
            # if nums1[i-1] is on the right side
            elif i > start and nums1[i-1] > nums2[j]:
                end = i - 1
            
            # found median
            else:               
                # i is 0 means that nums1 is on the right side
                # same j
                left_max = max(
                    nums1[i-1] if i != 0 else 0,
                    nums2[j-1] if j != 0 else 0
                )
                
                if (n + m) % 2 == 1:
                    return left_max
                
                else:
                    # i is n means that nums1 is on the left side
                    right_min = min(
                        nums1[i] if i != n else float("inf"),
                        nums2[j] if j != m else float("inf")
                    )
                    return (left_max + right_min) / 2
