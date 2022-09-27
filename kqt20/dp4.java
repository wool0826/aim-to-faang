//Best Time to Buy and Sell Stock with Cooldown 해결 완료
class Solution {
    private int[] prices;
    private int[][][] memo;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.memo = new int[prices.length][2][2];
        return dp(0,0,0);
    }

    private int dp(int i, int cooldown, int holding){
        if(i == prices.length)
            return 0;
        if(memo[i][cooldown][holding] == 0){
            if(cooldown == 0){
                int doNothing = dp(i+1, cooldown, holding);
                int doSomething;
                if(holding == 1)
                    //sell
                    doSomething = prices[i] + dp(i+1, 1, 0);
                else
                    //buy
                    doSomething = -prices[i] + dp(i+1, 0, 1);

                //최대값을 구하는 것이므로 [사거나 팔았을 때] 와 [아무것도 안했을 때] 어느쪽이 더 높은지 비교후 높은걸 저장
                memo[i][cooldown][holding] = Math.max(doNothing, doSomething);

            }else{
                //쿨다운일때는 아무것도 안하고 넘어가므로 현재 위치의 메모에 그대로 저장
                memo[i][cooldown][holding] = dp(i+1, 0, holding);
            }
        }

        return memo[i][cooldown][holding];
    }
}