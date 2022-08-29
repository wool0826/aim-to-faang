class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        """
        def showLcs(table):
            cols = len(table)
            rows = len(table[0])
            cnt = 0
            for i in range(rows+9):
                if i < 5:
                    print(' ', end = '')
                elif i >= 5 and i % 2 == 0:
                    print(text1[cnt], end = '')
                    cnt += 1
                elif i > 5 and i % 2 != 0:
                    print(',', end = '')
            print()
            
            for i in range(cols):
                for j in range(rows):
                    if i == 0 and j == 0:
                        print('  [', end = '')
                    elif i != 0 and j == 0:
                        print(text2[i-1]+' [', end = '')
                    if j != rows-1:
                        print(table[i][j], end = ',')
                    else:
                        print(table[i][j], end = '')
                print(']')
        """
        
        len1 = len(text1)
        len2 = len(text2)
        
        lcs = [[0 for j in range(len1+1)] for i in range(len2+1)]

        for i in range(1,len2+1):
            for j in range(1,len1+1):
                # print("i="+str(i)+", j="+str(j))
                # print("text2 = "+text2[i-1]+", text1 = "+text1[j-1])
                if text1[j-1] == text2[i-1]:
                    lcs[i][j] = lcs[i-1][j-1]+1
                else:
                    lcs[i][j] = max(lcs[i-1][j],lcs[i][j-1])
                # showLcs(lcs)
                
        return lcs[len2][len1]
