package interview.leetcode.prob;

/**
 * You are given an integer array nums. You must perform exactly one operation where you can replace one element nums[i] with nums[i] * nums[i]. 

Return the maximum possible subarray sum after exactly one operation. The subarray must be non-empty.

 

Example 1:

Input: nums = [2,-1,-4,-3]
Output: 17
Explanation: You can perform the operation on index 2 (0-indexed) to make nums = [2,-1,16,-3]. Now, the maximum subarray sum is 2 + -1 + 16 = 17.
Example 2:

Input: nums = [1,-1,1,1,-1,-1,1]
Output: 4
Explanation: You can perform the operation on index 1 (0-indexed) to make nums = [1,1,1,1,-1,-1,1]. Now, the maximum subarray sum is 1 + 1 + 1 + 1 = 4.
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
Seen this question in a real interview before?
1/5
Yes
No
Accepted
12.4K
Submissions
19K
Acceptance Rate
65.2%
 * 
 * Dec 30, 2024 - 8:26:14 PM
 * Jojo 
 */
public class MaximumSubarraySumAfterOneOperation {
	public int maxSumAfterOperation(int[] nums) {
        return bottomup(nums);

        // topdown is not intuitive. 
    }

    private int bottomup(int[] nums){
        int len = nums.length;
        int[][] dp = new int[len][2];

        // 0: contains the max array without square 
        // 1: contains the max array with 1 square
        dp[0][0] = nums[0];
        dp[0][1] = nums[0] * nums[0];

        int max = dp[0][1];

        for(int i=1; i<len; i++){
            // start a new series or continue the previous. 
            dp[i][0] = Math.max(nums[i], dp[i-1][0] + nums[i]);

            dp[i][1] = Math.max(
                // max of starting a new series or adding to the previous series
                Math.max(nums[i] * nums[i], dp[i-1][0] + (nums[i] * nums[i])), 
                // not adding cur idx as square
                dp[i-1][1] + nums[i]);

            // since the ans needs max after exactly one operation.
            max = Math.max(max, dp[i][1]);
        }

        return max;
    }
}
