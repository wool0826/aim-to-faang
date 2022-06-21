class Solution:
    def uniqueLetterString(self, s: str) -> int:
        result = 0  # 결과
        arr1 = []   # 부분 문자열을 담아두는 임시 배열
        
        length = len(s)
        
        # 부분 문자열 생성
        for i in range(length):
            for j in range(i,length):
                arr1.append(s[i:j+1])
        
        for i in range(len(arr1)):
            x = arr1[i]     # 부분 문자열 x
            arr2 = []       # 부분 문자열의 요소로 이루어진 배열
            
            for i in range(len(x)):
                arr2.append(x[i])
                
            count={}    # 부분 문자열의 요소 개수를 세는 배열
            
            for i in arr2:
                try: count[i] += 1
                except: count[i] = 1
                    
            for key in count:
                if count[key] == 1:
                    result += 1
                
        return result
