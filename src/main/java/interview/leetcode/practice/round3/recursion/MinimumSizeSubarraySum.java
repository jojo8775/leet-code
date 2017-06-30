package interview.leetcode.practice.round3.recursion;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int beg = 0, end = 0, len = nums.length, curSum = 0, result = Integer.MAX_VALUE; 
        
        while(end < len){
            curSum += nums[end++];
            
            while(curSum >= s){
                result = Math.min(result, end - beg);
                curSum -= nums[beg++];
            }
        }
        
        return result == Integer.MAX_VALUE ? 0 : result;
    } 
}
