package interview.leetcode.prob;

/**
 * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.

A subarray is defined as a contiguous sequence of numbers in an array.

A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1. Note that a subarray of size 1 is ascending.

 

Example 1:

Input: nums = [10,20,30,5,10,50]
Output: 65
Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.
Example 2:

Input: nums = [10,20,30,40,50]
Output: 150
Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
Example 3:

Input: nums = [12,17,15,13,10,11,12]
Output: 33
Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
Accepted
35,774
Submissions
56,010
 * @author jojo
 * Oct 5, 2022 8:30:37 PM
 */
public class MaximumAscendingSubarraySum {
    public int maxAscendingSum(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        
        int curVal = nums[0], maxVal = nums[0];
        
        for(int i=1; i<nums.length; i++){
            if(nums[i] <= nums[i-1]){
                maxVal = Math.max(maxVal, curVal);
                curVal = 0;
            }
            
            curVal += nums[i];
        }
        
        return Math.max(curVal, maxVal);
    }
}
