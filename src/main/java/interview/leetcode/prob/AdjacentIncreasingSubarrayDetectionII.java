package interview.leetcode.prob;

import java.util.List;

/**
 * Given an array nums of n integers, your task is to find the maximum value of k for which there exist two adjacent subarrays of length k each, such that both subarrays are strictly increasing. Specifically, check if there are two subarrays of length k starting at indices a and b (a < b), where:

Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
The subarrays must be adjacent, meaning b = a + k.
Return the maximum possible value of k.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [2,5,7,8,9,2,3,4,3,1]

Output: 3

Explanation:

The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
These two subarrays are adjacent, and 3 is the maximum possible value of k for which two such adjacent strictly increasing subarrays exist.
Example 2:

Input: nums = [1,2,3,4,4,4,4,5,6,7]

Output: 2

Explanation:

The subarray starting at index 0 is [1, 2], which is strictly increasing.
The subarray starting at index 2 is [3, 4], which is also strictly increasing.
These two subarrays are adjacent, and 2 is the maximum possible value of k for which two such adjacent strictly increasing subarrays exist.
 

Constraints:

2 <= nums.length <= 2 * 105
-109 <= nums[i] <= 109
Accepted
13,734
Submissions
37,565
 * 
 * Nov 11, 2024 - 8:18:42 AM
 * Jojo 
 */
public class AdjacentIncreasingSubarrayDetectionII {
	public int maxIncreasingSubarrays(List<Integer> nums) {
        int prevLen = 0, curBeg = 0;
        
        int curLen = 1;
        int max = 1;
        
        for(int i=1; i<nums.size(); i++){
            if(nums.get(i-1) < nums.get(i)){
                curLen++;
                
                max = Math.max(max, curLen/2);
                //System.out.println("same arr: " + max + "  curLen: " + curLen + "  len: " + curLen / 2);
            }
            else{
                max = Math.max(max, Math.min(prevLen, curLen));
                
                //System.out.println("diff arr: " + max + "  curLen: " + curLen + "  prevLen: " + prevLen);
                
                curBeg = i;
                prevLen = curLen;
                curLen = 1;
            }
        }
        
        // when series are like [5,8,-2,-1] last time validation is needed.
        max = Math.max(max, Math.min(prevLen, curLen));
        
        return max;
    }
}
