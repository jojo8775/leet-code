package interview.leetcode.prob;

/**
 * 

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 * @author jojo
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] nums, int target) {
        int beg = 0, end = nums.length - 1;
        
        // finding the starting index
        while(beg < end){
            // mid is left biased
            int mid = (end - beg)/2 + beg;
            
            if(nums[mid] < target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        // if target doesn't exists
        if(nums.length == 0 || nums[beg] != target){
            return new int[]{-1, -1};
        }
        
        int idx1 = beg;
        
        // resetting the end but re-using the beg from the previous computation. 
        end = nums.length - 1;
        
        // finding the ending index
        while(beg < end){
            
            // adding one to get mid to right biased
            int mid = (end - beg)/2 + beg + 1;
            
            if(nums[mid] > target){
                end = mid - 1;
            }
            else{
                beg = mid;
            }
        }
        
        return new int[]{idx1, end};
    }
}
