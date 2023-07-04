package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given a 0-indexed array nums of distinct integers. You want to rearrange the elements in the array such that every element in the rearranged array is not equal to the average of its neighbors.

More formally, the rearranged array should have the property such that for every i in the range 1 <= i < nums.length - 1, (nums[i-1] + nums[i+1]) / 2 is not equal to nums[i].

Return any rearrangement of nums that meets the requirements.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: [1,2,4,5,3]
Explanation:
When i=1, nums[i] = 2, and the average of its neighbors is (1+4) / 2 = 2.5.
When i=2, nums[i] = 4, and the average of its neighbors is (2+5) / 2 = 3.5.
When i=3, nums[i] = 5, and the average of its neighbors is (4+3) / 2 = 3.5.
Example 2:

Input: nums = [6,2,0,9,7]
Output: [9,7,6,2,0]
Explanation:
When i=1, nums[i] = 7, and the average of its neighbors is (9+6) / 2 = 7.5.
When i=2, nums[i] = 6, and the average of its neighbors is (7+2) / 2 = 4.5.
When i=3, nums[i] = 2, and the average of its neighbors is (6+0) / 2 = 3.
 

Constraints:

3 <= nums.length <= 105
0 <= nums[i] <= 105
Accepted
26,589
Submissions
53,899
 * @author jojo
 * Jul 4, 2023 9:24:36 AM
 */
public class ArrayWithElementNotEqToAvgOfNei {
	class Solution {
	    public int[] rearrangeArray_adv(int[] nums) {
	        Arrays.sort(nums);
	        
	        for(int i=1; i<nums.length; i+=2){
	            int temp = nums[i];
	            nums[i] = nums[i-1];
	            nums[i-1] = temp;
	        }
	        
	        return nums;
	    }
	    
	    public int[] rearrangeArray(int[] nums) {
	        
	        if (nums == null || nums.length == 0) {
	            return nums;
	        }

	        final int numsLength = nums.length;

	        // based on the problem statement making adjustments from left to right. 
	        for (int i = 1; i < numsLength - 1; i++) {
	            if (nums[i] * 2 == nums[i - 1] + nums[i + 1]) {
	                swap(nums, i, i + 1);
	            }
	        }

	        // this is needed from right to left to make sure after swap the logic still holds
	        for (int i = numsLength - 2; i >= 1; i--) {
	            if (nums[i] * 2 == nums[i + 1] + nums[i - 1]) {
	                swap(nums, i, i + 1);
	            }
	        }

	        return nums;
	    }
	    
	    private void swap (int[] arr, int i1, int i2) {
	        int x = arr[i1];
	        arr[i1] = arr[i2];
	        arr[i2] = x;
	    }
	}
}
