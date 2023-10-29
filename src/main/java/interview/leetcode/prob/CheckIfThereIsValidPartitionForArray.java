package interview.leetcode.prob;

/**
 * You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.

We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:

The subarray consists of exactly 2, equal elements. For example, the subarray [2,2] is good.
The subarray consists of exactly 3, equal elements. For example, the subarray [4,4,4] is good.
The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
Return true if the array has at least one valid partition. Otherwise, return false.

 

Example 1:

Input: nums = [4,4,4,5,6]
Output: true
Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.
Example 2:

Input: nums = [1,1,1,2]
Output: false
Explanation: There is no valid partition for this array.
 

Constraints:

2 <= nums.length <= 105
1 <= nums[i] <= 106
Accepted
80,080
Submissions
154,101
 * @author jojo
 * Oct 29, 2023 2:55:20 PM
 */
public class CheckIfThereIsValidPartitionForArray {
	public boolean validPartition(int[] nums) {
        int len = nums.length;
        
        boolean[] dp = new boolean[len + 1]; // maintain states
        dp[0] = true; // base case
        
        // relations based on the problem statement 
        for(int i=2; i<=len; i++){
            int idx = i-1;
            
            if(idx >= 1 && nums[idx - 1] == nums[idx]){
                dp[i] |= dp[i - 2]; // use case when two elements are same 
            }
            
            if(idx >=2 && nums[idx-1] == nums[idx-2] && nums[idx] == nums[idx-1]){
                dp[i] |= dp[i - 3]; // use case when three elements are same 
            }
            
            if(idx >=2 && nums[idx-1] == nums[idx-2] + 1 && nums[idx] == nums[idx-1] + 1){
                dp[i] |= dp[i - 3]; // use case when three elements are consequitive. 
            }
        }
        
        return dp[len];
    }
}
