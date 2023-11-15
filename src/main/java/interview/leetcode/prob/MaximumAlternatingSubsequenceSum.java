package interview.leetcode.prob;

/**
 * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.

For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).

A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.

 

Example 1:

Input: nums = [4,2,5,3]
Output: 7
Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
Example 2:

Input: nums = [5,6,7,8]
Output: 8
Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
Example 3:

Input: nums = [6,2,1,2,4,5]
Output: 10
Explanation: It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
Accepted
4,979
Submissions
8,936
 * @author jojo
 * Jun 29, 2021  12:53:34 AM
 */
public class MaximumAlternatingSubsequenceSum {
	public long maxAlternatingSum(int[] nums) {
        //return topDown(nums);
        return bottomUp(nums);
        //return bottomUp_adv(nums);
    }
    
    private long bottomUp(int[] nums) {
        int len = nums.length;
        
        // states
        // memo[0] = even sum 
        // memo[1] = odd sum
        long[][] memo = new long[2][len + 1];
        
        for(int i=0; i<len; i++){
            // for even current sign = 1 
            // max even = pev even or cur sum + max of pev odd (refer to the topDown soltuion relation)
            memo[0][i+1] = Math.max(memo[0][i], memo[1][i] + nums[i]); 
            
            // for even current sign = -1 
            // max odd = pev odd or (-)cur sum + max of perv even (refer to the topDown soltuion relation)
            memo[1][i+1] = Math.max(memo[1][i], memo[0][i] - nums[i]); 
        }
        
        return Math.max(memo[0][len], memo[1][len]);
    }
    
    private long bottomUp_adv(int[] nums) {
        // maintaining two variables for even and odd index sub array 
        long evenSum = 0, oddSum = 0;
        long result = 0;
        
        for(int n : nums){
            // calculating next Even sum by adding current to the previously computed oddSum
            long nextEvenSum = Math.max(evenSum, oddSum + n); 
            
            // calculating next Odd sum by removing the current from previously computed eventSum as per the problem
            long nextOddSum = Math.max(oddSum, evenSum - n);
            
            result = Math.max(nextOddSum, nextEvenSum);
            evenSum = nextEvenSum;
            oddSum = nextOddSum;
        }
        
        return result;
    }
    
    private long topDown(int[] nums){
        Long[][] memo = new Long[2][nums.length];
        
        return dp(1, 0, nums, memo);
    }
    
    private long dp(int sign, int idx, int[] nums, Long[][] memo){
        if(idx == nums.length){
            return 0;
        }
        
        int memoIdx = sign == 1 ? 0 : 1;
        if(memo[memoIdx][idx] != null){
            return memo[memoIdx][idx];
        }
        
        long including = (sign * nums[idx]) + dp((sign * -1), idx + 1, nums, memo);
        long excluding = dp(sign, idx + 1, nums, memo);
        
        return memo[memoIdx][idx] = Math.max(including, excluding);
    }
}
