package interview.leetcode.prob;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
Subscribe to see which companies asked this question.
 * @author jojo
 *
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        // sum[P] - sum[N] = Target
        // sum[P] - sum[N] + sum[P] + sum[N] = Target + sum[P] + sum[N]
        // 2sum[P] = target + sum[Total]
        // sum[p] = (target + sum[Total]) / 2
        
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        
        if(S > sum || (S + sum) % 2 > 0){
            return 0;
        }
        
        // finding a number of ways subset (target + sum / 2) can constructed 
        return findSubset(nums, (S + sum) >> 1);
    }
    
    private int findSubset(int[] arr, int target){
        int[] dp = new int[target + 1];
        
        // since there is only one way to create a subset of 0 value
        dp[0] = 1;
        
        for(int num : arr){
            for(int i=target; i>= num; i--){
                dp[i] += dp[i - num];
            }
        }
        
        return dp[dp.length - 1];
    }
}
