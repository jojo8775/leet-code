package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
Example 2:

Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.
 

Constraints:

1 <= k <= nums.length <= 105
1 <= nums[i] <= 105
Accepted
11,986
Submissions
38,344
 * @author jojo
 * Nov 6, 2022 10:34:21 AM
 */
public class MaximumSumOfDistinctSubarraysWithLengthK {
    public long maximumSubarraySum(int[] nums, int k) {
        int len = nums.length;
        
        if(k > len){
            return -1;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        
        long curSum = 0, maxSum = 0;
        
        for(int i=0; i<k-1; i++){
            int val = map.getOrDefault(nums[i], 0);
            if(val != 0){
                count++;
            }
            map.put(nums[i], val + 1);
            curSum += nums[i];
        }
        
        for(int i=k-1, j=0; i<len; i++,j++){
            int val = map.getOrDefault(nums[i], 0);
            if(val != 0){
                count++;
            }
            
            map.put(nums[i], val + 1);
            curSum += nums[i];
            
            if(count == 0){
                maxSum = Math.max(maxSum, curSum);
            }
            
            curSum -= nums[j];
            int val2 = map.get(nums[j]);
            if(val2 > 1){
                count--;
            }
            map.put(nums[j], val2 - 1);
        }
        
        return maxSum;
    }
}
