package interview.leetcode.practice.round4.arrays;

public class MaximumProductSubArray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        
        max[0] = nums[0];
        min[0] = nums[0];
        
        int maxValue = nums[0];
        
        for(int i=1; i<nums.length; i++){
            if(nums[i] > 0){
                max[i] = Math.max(nums[i], max[i-1] * nums[i]);
                min[i] = Math.min(nums[i], min[i-1] * nums[i]);
            }
            else{
                max[i] = Math.max(nums[i], min[i-1] * nums[i]);
                min[i] = Math.min(nums[i], max[i-1] * nums[i]);
            }
            
            maxValue = Math.max(maxValue, max[i]);
        }
        
        return maxValue;
    }
}
