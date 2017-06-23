package interview.leetcode.practice.round4.arrays;

public class TrappingRainWater {
    public int trap(int[] height) {
        if(height.length == 0){
            return 0;
        }
        
        int[] dp = new int[height.length];
        dp[0] = height[0];
        
        for(int i=1; i<height.length; i++){
            dp[i] = Math.max(dp[i-1], height[i]);
        }
        
        int maxRight = 0, result = 0;
        
        for(int i=height.length -1; i>0; i--){
            int val = Math.max(0, Math.min(dp[i-1], maxRight) - height[i]);
            result += val;
            
            maxRight = Math.max(maxRight, height[i]);
        }
        
        return result;
    }
}
