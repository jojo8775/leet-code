package interview.leetcode.prob;

/**
 * You are given an integer array nums and two integers minK and maxK.

A fixed-bound subarray of nums is a subarray that satisfies the following conditions:

The minimum value in the subarray is equal to minK.
The maximum value in the subarray is equal to maxK.
Return the number of fixed-bound subarrays.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
Output: 2
Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
Example 2:

Input: nums = [1,1,1,1], minK = 1, maxK = 1
Output: 10
Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 

Constraints:

2 <= nums.length <= 105
1 <= nums[i], minK, maxK <= 106
Accepted
7,686
Submissions
19,381
 * @author jojo
 * Oct 19, 2022 7:52:19 PM
 */
public class CountSubarraysWithFixedBounds {
	public long countSubarrays(int[] nums, int minK, int maxK) {
        // last index of an entry which is greater than max or less than min
        int lastBadIdx = -1;
        
        // initial index of max and min index are set to -1;
        int lastMinIdx = -1, lastMaxIdx = -1;
        
        long result = 0;
        
        for(int i=0; i<nums.length; i++){
            // tracking the last bad index;
            if(nums[i] < minK || nums[i] > maxK){
                lastBadIdx = i;
            }
            
            if(nums[i] == minK){
                lastMinIdx = i;
            }
            
            if(nums[i] == maxK){
                lastMaxIdx = i;
            }
            
            // taking the left most max or min index in the sub array so that all the sub array
            // which has a bad index can be removed. 
            int min = Math.min(lastMaxIdx, lastMinIdx);
            
            // take example of arr = [8,6,2,1,4]
            // number of sub array ending at index 3 (arr[3]) are 4
            // [1],[2,1],[6,2,1],[8,6,2,1]
            // but let say the range is max = 6 and min = 2; in this case index 3 is leftmost bad index 
            // so the and sub array 1, 2 and 4 needs to be removed.
            result += Math.max(0, (min - lastBadIdx));
        }
        
        return result;
    }
}
