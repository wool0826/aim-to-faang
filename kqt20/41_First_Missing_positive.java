class Solution {
    public int firstMissingPositive(int[] nums) {
    
        int min = 1;
        int max = nums.length;
        boolean[] resultSet = new boolean[500001];
        int result=0;
        
        for(int i=0; i<max; i++){
            if(nums[i] >= min && nums[i] <= max){
                resultSet[nums[i]-1] = true;
            }
            }
        for(int i=0; i<=max; i++){
            if(!resultSet[i]){
                result = i+1;
                break;
                }
            }
        return result;
    }
}
