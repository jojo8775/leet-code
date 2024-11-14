package interview.leetcode.prob;

import java.util.List;

/**
 * Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing. Specifically, check if there are two subarrays starting at indices a and b (a < b), where:

Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
The subarrays must be adjacent, meaning b = a + k.
Return true if it is possible to find two such subarrays, and false otherwise.

 

Example 1:

Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3

Output: true

Explanation:

The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
These two subarrays are adjacent, so the result is true.
Example 2:

Input: nums = [1,2,3,4,4,4,4,5,6,7], k = 5

Output: false

 

Constraints:

2 <= nums.length <= 100
1 < 2 * k <= nums.length
-1000 <= nums[i] <= 1000
Accepted
22,177
Submissions
56,258
 * 
 * Nov 11, 2024 - 7:54:06 AM
 * Jojo 
 */
public class AdjacentIncreasingSubarrayDetectionI {
	public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int winlen = k + k;
        for(int i=0; i<nums.size(); i++){
            if(i + winlen - 1 >= nums.size()){
                break;
            }
            
            int beg = i, end = i + winlen - 1;
            int mid = beg + (end - beg)/2;
            
            if(isIncreasing(beg, mid, nums) && isIncreasing(mid + 1, end, nums)){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isIncreasing(int beg, int end, List<Integer> nums){
        for(int i = beg + 1; i<=end; i++){
            if(nums.get(i-1) >= nums.get(i)){
                return false;
            }
        }
        
        return true;
    }
}
