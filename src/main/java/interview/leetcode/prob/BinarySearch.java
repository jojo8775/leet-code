package interview.leetcode.prob;

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

 

Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
 

Constraints:

1 <= nums.length <= 104
-9999 <= nums[i], target <= 9999
All the integers in nums are unique.
nums is sorted in an ascending order.
Accepted
255,299
Submissions
469,467
 * @author jojo
 * Apr 22, 2021  11:09:21 PM
 */
public class BinarySearch {
    public int search(int[] nums, int target) {
        int beg = 0, end = nums.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(nums[mid] == target){
                return mid;
            }
            
            if(nums[mid] > target){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return -1;
    }
}
