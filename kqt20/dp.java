import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//delete and earn 해결 완료
class Solution {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> memo = new TreeMap<>();
        Map<Integer, Integer> dp = new TreeMap<>();
        int max = 0;
        for(int num : nums){
            memo.put(num, memo.getOrDefault(num, 0) + 1);
            max = Math.max(num, max);
        }

        dp.put(1, memo.getOrDefault(1, 0));
        dp.put(2, Math.max(memo.getOrDefault(2, 0) * 2, dp.getOrDefault(1, 0)));
        memo.remove(1);
        memo.remove(2);

        for(Map.Entry<Integer, Integer> entry : memo.entrySet()){
            int i = entry.getKey();
            dp.put(i, Math.max(dp.getOrDefault(i-1, 0), dp.getOrDefault(i-2, 0) + entry.getValue() * i));
        }

        return dp.get(max);
    }
}