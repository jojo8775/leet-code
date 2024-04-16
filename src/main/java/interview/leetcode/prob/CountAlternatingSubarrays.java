package interview.leetcode.prob;

/**
 * You are given a binary array nums.

We call a subarray alternating if no two adjacent elements in the subarray have the same value.

Return the number of alternating subarrays in nums.

 

Example 1:

Input: nums = [0,1,1,1]

Output: 5

Explanation:

The following subarrays are alternating: [0], [1], [1], [1], and [0,1].

Example 2:

Input: nums = [1,0,1,0]

Output: 10

Explanation:

Every subarray of the array is alternating. There are 10 possible subarrays that we can choose.

 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
Accepted
27,846
Submissions
49,525
 * 
 * Apr 10, 2024 - 10:48:20 PM
 * Jojo 
 */
public class CountAlternatingSubarrays {
	public long countAlternatingSubarrays(int[] nums) {
        // first element regradless 0 or 1 always qualifies
        long result = 1, curCount = 1;
        
        // implementing simple count logic 
        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[i-1]){
                // if the numbers are not different resetting the count
                curCount = 0;
            }
            
            curCount += 1;
            result += curCount;
        }
        
        return result;
    }
}
