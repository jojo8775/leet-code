package interview.leetcode.prob;

/**
 * Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].

The test cases are generated so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [2,1,4,3], left = 2, right = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Example 2:

Input: nums = [2,9,2,5,6], left = 2, right = 8
Output: 7
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= left <= right <= 109
 * @author jojo
 * Jan 26, 2022 10:59:14 PM
 */
public class NumberOfSubarraysWithBondedMaximum {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        //left -1 because we want all the sub array including left and right
        return countSubArray(nums, right) - countSubArray(nums, left - 1);
    }
    
    // idea is to count all the continuous sub arrays which has elements <= target.
    // the moment an element is above target the subarray size become 0, (cur = 0)
    private int countSubArray(int[] nums, int target){
        int result = 0, cur = 0;
        
        for(int n : nums){
            if(n <= target){
                // think of arr [1, 2, 3]
                // cur = 1; 2; 3;
                // ans = 1; 3; 6;
                cur += 1;
                result += cur;
            }
            else{
                cur = 0;
            }
        }
        
        return result;
    }
}
