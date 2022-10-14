package interview.leetcode.prob;

/**
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.

 

Example 1:

Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.
Example 2:

Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
Example 3:

Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
Accepted
72,948
Submissions
167,041
 * @author jojo
 * Oct 13, 2022 11:51:37 PM
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {
	public int getMaxLen(int[] nums) {
        int firstNegPos = -1, startPos = -1, len = 0, negCount = 0;
        
        for(int i=0; i<nums.length; i++){
            // if num is neg, we need to keep a track of the count unless found a 0
            if(nums[i] < 0){
                negCount++;
                
                // need to update the first -ve position in a span. 
                firstNegPos = firstNegPos == -1 ? i : firstNegPos;
            }
            
            // if cur is 0 we need to reset the span. 
            if(nums[i] == 0){
                negCount = 0;
                startPos = i;
                firstNegPos = -1;
            }
            else{
                // if -ve count is even then consider start pos.
                if(negCount % 2 == 0){
                    len = Math.max(len, i - startPos);
                }
                // if -ve count is odd, then consider starting from first -ve position.  
                else{
                    len = Math.max(len, i - firstNegPos);
                }
            } 
        }
        
        return len;
    }
}
