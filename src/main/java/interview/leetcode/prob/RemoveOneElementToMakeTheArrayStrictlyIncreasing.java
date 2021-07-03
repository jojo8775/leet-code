package interview.leetcode.prob;

/**
 * Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly one element, or false otherwise. If the array is already strictly increasing, return true.

The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).

 

Example 1:

Input: nums = [1,2,10,5,7]
Output: true
Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
[1,2,5,7] is strictly increasing, so return true.
Example 2:

Input: nums = [2,3,1,2]
Output: false
Explanation:
[3,1,2] is the result of removing the element at index 0.
[2,1,2] is the result of removing the element at index 1.
[2,3,2] is the result of removing the element at index 2.
[2,3,1] is the result of removing the element at index 3.
No resulting array is strictly increasing, so return false.
Example 3:

Input: nums = [1,1,1]
Output: false
Explanation: The result of removing any element is [1,1].
[1,1] is not strictly increasing, so return false.
Example 4:

Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is already strictly increasing, so return true.
 

Constraints:

2 <= nums.length <= 1000
1 <= nums[i] <= 1000
Accepted
7,683
Submissions
24,792
 * @author jojo
 * Jun 29, 2021  1:58:58 AM
 */
public class RemoveOneElementToMakeTheArrayStrictlyIncreasing {
	public boolean canBeIncreasing_adv(int[] nums) {
        
        int count = 0;
        for(int i=1; i<nums.length && count < 2; i++){
            if(nums[i-1] >= nums[i]){
                count ++;
                
                if(i > 1 && nums[i-2] >= nums[i]){
                    nums[i] = nums[i-1];
                }
            }
        }
        
        return count < 2;
    }
	
	
    public boolean canBeIncreasing(int[] nums) {
        if(nums.length == 0){
            return true;
        }
        
        int leftCount = 0, rightCount = 0;
        
        int maxLeftToRight = nums[0];
        
        for(int i=1; i<nums.length; i++){
            if(maxLeftToRight >= nums[i]){
                leftCount++;
            }
            
            maxLeftToRight = Math.max(maxLeftToRight, nums[i]);
        }
        
        
        int minRightToLeft = nums[nums.length - 1];
        
        for(int i=nums.length - 2; i>=0; i--){
            if(minRightToLeft <= nums[i]){
                rightCount++;
            }
            
            minRightToLeft = Math.min(minRightToLeft, nums[i]);
        }
        
        return leftCount <= 1 || rightCount <= 1;
    }
}
