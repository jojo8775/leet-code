package interview.leetcode.prob;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
 * @author jojo
 *
 */
public class RangeSumQuery {
    private int[] dp;
    
    public RangeSumQuery(int[] nums) {
        if(nums == null || nums.length < 1){
            return;
        }
        
        dp = new int[nums.length];
        
        //compute sum
        dp[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            dp[i] = nums[i] + dp[i-1];
        }
    }

    public int sumRange(int i, int j) {
        if(dp == null){
            return -1;
        }
        
        if(i == 0){
            return dp[j];
        }
        
        return dp[j] - dp[i-1];
    }
}
