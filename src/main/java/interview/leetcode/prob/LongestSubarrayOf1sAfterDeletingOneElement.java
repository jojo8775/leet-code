package interview.leetcode.prob;

/**
 * Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 

Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
352.2K
Submissions
515.7K
Acceptance Rate
68.3%
 * 
 * Dec 17, 2024 - 4:30:52 PM
 * Jojo 
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
	public int longestSubarray(int[] nums) {
        int result = 0;
        int count = 2, oneCount = 0;

        for(int j=0,i=0; i<nums.length; i++){
            if(nums[i] == 0){
                count--;
            }
            else{
                oneCount++;
            }

            while(count == 0){
                if(nums[j] == 0){
                    count++;
                }
                else{
                    oneCount--;
                }
                j++;
            }

            result = Math.max(result, oneCount);
        }

        return result == nums.length ? (result - 1) : result;
    }
}
