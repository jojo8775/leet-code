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
	public int findTargetSumWays(int[] nums, int target) {
        return topDown(nums, target);
    }
	
	private int bottomup(int[] nums, int target){
        int sum = 0;
        
        for(int n : nums){
            sum += n;
        }
        
        if(Math.abs(target) > sum) {
        	return 0;
        }
        
        int targetRange = sum * 2 + 1;
        
        int[][] dp = new int[nums.length][targetRange];
        dp[0][sum + nums[0]] = 1;
        dp[0][sum - nums[0]] += 1;
        
        
        for(int i=1; i<nums.length; i++){
            for(int j=0; j<targetRange; j++){
                if(dp[i-1][j] > 0){
                    dp[i][j + nums[i]] += dp[i-1][j];
                    dp[i][j - nums[i]] += dp[i-1][j];
                }
            }
        }
        
        return dp[nums.length - 1][sum + target];
    }
    
    private int topDown(int[] nums, int target){
        int sum = 0;
        
        for(int n : nums){
            sum += n;
        }
        
        // for each index the possibilities are from -9 to +9 if the total is 9
        Integer[][] memo = new Integer[nums.length][(2 * sum) + 1];
        
        return dp(nums, 0, 0, target, memo, sum);
    }
    
    private int dp(int[] nums, int idx, int sum, int target, Integer[][] memo, int total){
        if(idx == nums.length){
            if(sum == target){
                return 1;
            }
            else{
                return 0;
            }
        }
        
        if(memo[idx][total + sum] != null){
            return memo[idx][total + sum];
        }
        
        int addition = dp(nums, idx + 1, sum + nums[idx], target, memo, total);
        int substract = dp(nums, idx + 1, sum - nums[idx], target, memo, total);
        
        return memo[idx][total + sum] = addition + substract;
    }
	
	
    public int findTargetSumWays_old(int[] nums, int S) {
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
