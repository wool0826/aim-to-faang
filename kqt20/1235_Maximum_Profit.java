class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        //startTime, endTime, profit과 같은 인덱스에 profit의 최대값을 저장하는 배열.
        //가장 초기값을 0으로 만들기 위해 길이를 n+1로 만든다.
        int[] maxSumsOfProfit = new int[n + 1];

        //문제를 돌리다보면 처음엔 startTime이 오름차순으로 정렬되어있는것처럼 보이다가 뒤에서 정렬이 안되는 케이스들이 나온다.
        //정렬이 다 되어있는 케이스들도 많으므로 Insertion Sort 를 실행한다.
        for(int i = 1; i < n; i++){
            int tmpStart = startTime[i];
            int tmpEnd = endTime[i];
            int tmpProfit = profit[i];

            int j = i-1;
            while(j >= 0 && startTime[j] > tmpStart){
                //정렬은 startTime만 기준으로 하고, 같은 인덱스에있는 endTime의 값과 profit의 값도 같이 옮겨준다. 
                startTime[j+1] = startTime[j];
                endTime[j+1] = endTime[j];
                profit[j+1] = profit[j];
                j--;
            }
            startTime[j+1] = tmpStart;
            endTime[j+1] = tmpEnd;
            profit[j+1] = tmpProfit;
        }

        for(int i = n - 1; i>=0 ; i--){
            //안겹치는곳 = startTime[i] , endTime[i] 사이에 startTime을 가지고 있지 않은 가장 먼저 나오는놈
            // => endTime[i] 보다 값이 크거나 같은 startTime[a]를 찾으면 된다.
            int notDuplicatedIndex = i+1;
            while(notDuplicatedIndex < n){
                //notDuplicatedIndex가 n이 되어도 maxSumOfProfit 배열에서는 상관없지만
                // startTime의 바운더리는 벗어나게 되므로
                // while과 if문으로 조건문을 나누어서 OutOfBoundary 오류가 뜨지 않게 한다.
                if(endTime[i] <= startTime[notDuplicatedIndex])
                    break;
                notDuplicatedIndex++;
            }

            //핵심 코드로, 현재 i보다 전에 계산햇던 최대값 vs 현재 i와 겹치지 않는 최대값 + i의 profit
            //둘중 더 큰쪽을 계속 저장해 나간다.
            maxSumsOfProfit[i] = Math.max(maxSumsOfProfit[i + 1],
                    maxSumsOfProfit[notDuplicatedIndex] + profit[i]);
        }

        //0에 저장된게 제일 큰 값이므로 리턴
        return maxSumsOfProfit[0];
    }
}