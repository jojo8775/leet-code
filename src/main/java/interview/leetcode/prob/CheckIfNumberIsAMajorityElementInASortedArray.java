package interview.leetcode.prob;

/**
 * Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.

A majority element is an element that appears more than N/2 times in an array of length N.

 

Example 1:

Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
Output: true
Explanation: 
The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 > 9/2 is true.
Example 2:

Input: nums = [10,100,101,101], target = 101
Output: false
Explanation: 
The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 > 4/2 is false.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 10^9
1 <= target <= 10^9
Accepted
21,095
Submissions
36,859
 * @author jojo
 * Apr 19, 2021  12:36:04 AM
 */
public class CheckIfNumberIsAMajorityElementInASortedArray {
	public boolean isMajorityElement(int[] nums, int target) {
        int idx1 = findFirstIndex(nums, target);
        if(idx1 == nums.length || nums[idx1] != target){
            return false;
        }
        
        int endIdx = findLastIndex(nums, target);
        // System.out.println("e idx : " + endIdx);
            
        return (endIdx - idx1 + 1) > (nums.length/2);
    }
    
    private int findFirstIndex(int[] nums, int target){
        int beg = 0, end = nums.length;
        
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            if(nums[mid] < target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return beg;
    }
    
    private int findLastIndex(int[] nums, int target){
        int beg = 0, end = nums.length - 1;
        
        while(beg < end){
            int mid = beg + (end - beg)/2 + 1;
            
            if(nums[mid] > target){
                end = mid - 1;
            }
            else{
                beg = mid;
            }
        }
        
        return end == nums.length ? end - 1 : end;
    }
}
