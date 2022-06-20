class Solution:
    def uniqueLetterString(self, s: str) -> int:
        import string
        
        # 각 문자 처음 위치
        index = {c: [-1, -1] for c in string.ascii_uppercase}
        res = 0
        
        # 각 위치의 문자가 언제 unique 할 수 있는지
        for i, c in enumerate(s):
            k, j = index[c]
            
            # 이전 위치에서 지금 위치까지 가능한 경우의 수
            res += (i - j) * (j - k)
            index[c] = [j, i]
            
        # 마지막까지
        for c in index.keys():
            k, j = index[c]
            res += (len(s) - j) * (j - k)
        return res
