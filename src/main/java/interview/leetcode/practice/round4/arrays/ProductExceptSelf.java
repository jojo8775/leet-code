package interview.leetcode.practice.round4.arrays;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if(nums.length < 2){
            return nums;
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        
        for(int i=1; i<dp.length; i++){
            dp[i] = dp[i-1] * nums[i];
        }
        
        int right = 1;
        for(int i=nums.length - 1; i>=0; i--){
            int temp = nums[i];
            int left = i==0 ? 1 : dp[i-1];
            nums[i] = left*right;
            right *= temp;
        }
        
        return nums;
    }
}
