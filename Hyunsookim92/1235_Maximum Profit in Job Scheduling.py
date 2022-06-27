from itertools import combinations

class Solution:
    def jobScheduling(self, startTime: List[int], endTime: List[int], profit: List[int]) -> int:
        jobName = []        # 작업명
        jobComb = []        # 작업들로 이루어진 조합
        profitSum = []      # 이익의 합계
        
        job = {}            # 작업 딕셔너리
        sortedJob = {}      # 정렬된 작업 딕셔너리
        
        n = len(startTime)  # 작업의 개수
        
        # 작업명 생성
        # jobName = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H']
        for i in range(n):
            jobName.append(chr(65+i))       
        
        # 작업 딕셔너리 생성
        # {'A': [6, 19, 2], 'B': [15, 18, 9], 'C': [7, 19, 1], 'D': [11, 16, 19], 'E': [1, 10, 5], 'F': [3, 8, 7], 'G': [16, 19, 3], 'H': [2, 8, 19]}
        for i in range(n):
            job[jobName[i]] = [startTime[i], endTime[i], profit[i]]
        
        # 작업 딕셔너리 정렬
        # {'A': [1, 10, 5], 'B': [2, 8, 19], 'C': [3, 8, 7], 'D': [6, 19, 2], 'E': [7, 19, 1], 'F': [11, 16, 19], 'G': [15, 18, 9], 'H': [16, 19, 3]}
        tmpJob = sorted(job.items(), key = lambda item : item[1])
        
        for i in range(n):
            st = tmpJob[i][1][0]                    # 작업시작시간
            et = tmpJob[i][1][1]                    # 작업종료시간
            pf = tmpJob[i][1][2]                    # 작업이익
            sortedJob[jobName[i]] = [st, et, pf]    # 정렬된 딕셔너리 생성
        
        # 작업들로 이루어진 조합 구하기
        # jobComb = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        #           ('A', 'B'), ('A', 'C'), ('A', 'D'), ('A', 'E'), ('A', 'F'), ...
        #           ('A', 'B', 'C'), ('A', 'B', 'D'), ('A', 'B', 'E'), ('A', 'B', 'F'), ...
        #           ]
        for i in range(n):
            jobComb.append(jobName[i])              # 한개짜리 작업 구하기
            
        for i in range(1,n+1):                      # 여러개로 이루어진 작업의 조합 구하기
            if i != 1:
                tmp = list(combinations(jobName, i))
                for i in range(len(tmp)):
                    jobComb.append(tmp[i])
                
        # 최대 작업 구하기
        for i in range(len(jobComb)):
            st = 0                                  # 작업시작시간 초기화
            et = 0                                  # 작업종료시간 초기화
            profitSum.append(0)                     # 이익의 합 초기화
            
            tmp = jobComb[i]                        # 작업 조합을 담는 임시 리스트
            
            for j in range(len(tmp)):
                if et <= sortedJob[tmp[j]][0]:              # 끝나는 시점이 다음 작업의 시작시간보다 작으면
                    st = sortedJob[tmp[j]][0]               # st를 다음 작업의 시작시간으로
                    et = sortedJob[tmp[j]][1]               # et를 다음 작업의 종료시간으로 한다.
                    profitSum[i] += sortedJob[tmp[j]][2]    # profit의 값을 profitSum에 더한다.

        return(max(profitSum))
