class Solution {
    public int firstMissingPositive(int[] nums) {

        int min = 1;
        int max = nums.length;

        int tmp;

        int result;

        for(int i=0; i<max; i++){
            while(nums[i] >= min && nums[i] <= max && nums[nums[i]-1] != nums[i]){
                tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            }
        }
        for(int i=0; i<max; i++){
            if(nums[i] < min || nums[i] > max || nums[i] != i+1){
                result = i+1;
                return result;
            }
        }
        result = max+1;
        return result;
    }
}