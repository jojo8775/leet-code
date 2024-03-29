package interview.leetcode.prob.paid;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.

We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

 

Example 1:

Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:

Input: nums = [4,2,1]
Output: false
Explanation: You cannot get a non-decreasing array by modifying at most one element.
 

Constraints:

n == nums.length
1 <= n <= 104
-105 <= nums[i] <= 105
Accepted
259,626
Submissions
1,053,398
 * 
 * @author jojo
 * Mar. 26, 2024 7:52:31 a.m.
 */
public class NonDecreaingArray {
	public boolean checkPossibility(int[] nums) {
        int violationCount = 0;
        
        for(int i=1; i<nums.length && violationCount < 2; i++){
            // based on the problem we need to correct this array to increeasing order. 
            // for this every element on the left of i should <= nums[i]
            // if nums[i-1] > nums[i] then there are two options
            if(nums[i-1] > nums[i]){
                violationCount++;
                
                // if nums[i-2] <= nums[i] then we can make nums[i-1] = nums[i] to correct the ordering
                // if i-1 is the first elelment then definitely we can make i-1 elememt to i
                if(i - 2 < 0 || nums[i-2]  <= nums[i]){
                    nums[i-1] = nums[i];
                }
                
                // if nums[i-2] > nums[i] then the only option is to make nums[i] = nums[i-1] so that the 
                // ordering is corrected. 
                else{
                    nums[i] = nums[i-1];
                }
            }
        }
        
        return violationCount < 2;
    }
}
