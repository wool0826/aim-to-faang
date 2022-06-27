class Solution {
public:
//dp + binary search
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        vector<vector<int>> V;
        for (int i = 0; i < startTime.size(); ++i) {
            V.push_back({ endTime[i], startTime[i], profit[i] });
        }//종료 시간 기준으로 정렬하기위해 벡터 가장앞에 작업 종료시간이 가도록 새로운 작업 벡터를 구성
        sort(V.begin(),V.end());//정렬
        vector<pair<int, int>> dp;// dp 벡터 생성
        dp.push_back({ 0, 0 });//dp[0]는 아무일도 하지않았을때, 따라서 가중치 0
        int res = 0;
         for (int i = 0; i < startTime.size(); ++i) {
            auto iter = upper_bound(dp.begin(), dp.end(),(pair<int,int>){V[i][1], (int)2e9});//이분탐색으로 현재 작업을 수용할 수 있는 최대의 dp[t]탐색
            if (iter != dp.begin()) {// 그러한 dp[t]가 있다면
                res = max(res, prev(iter)->second + V[i][2]);// 리턴값 갱신
                dp.push_back({ V[i][0], res });// dp 추가 
            }
        }
        return res;
    }
};