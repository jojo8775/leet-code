package interview.leetcode.prob;

/**
 * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.
 * @author jojo
 *Nov 2, 201712:34:33 AM
 */
public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int beg = 0, end = nums.length - 1;
        
        while(beg < end){
            int mid = beg + (end - beg) / 2;
            
            // if mid == 0 (for use case 1,2,2,3,3) or if mid happens to be the unique entry return that. 
            if(mid == 0 || (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])){
                return nums[mid];
            }
            
            // if the input is 1,1,2,2,3,... and if last 2 is mid then left half cannot have the unique element.
            if(nums[mid] == nums[mid - 1] && mid % 2 == 1){
                beg = mid + 1;
            }
            // if the input is 1,1,3,3... and if first 3 is mid then left half cannot have the unique element.
            else if(nums[mid] == nums[mid + 1] && mid % 2 == 0){
                beg = mid + 1;
            }
            
            else{
                end = mid - 1;
            }
        }
        
        return nums[end];
    }
}
