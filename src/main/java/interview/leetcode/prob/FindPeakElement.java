package interview.leetcode.prob;

/**
 * A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * @author jojo
 *
 */
public class FindPeakElement {
	public int findPeakElement(int[] nums) {
        // given the array nums, it can have 3 cases 
        // (1) all the numbers are in asc order : in this case the peak is right most element
        // (2) all the numbers are in desc order : in this case the peak is left most element
        // (3) the peak is in middle : in this case the mid > mid + 1 and mid > mid - 1 element
        int left = 0, right = nums.length - 1;
        
        while(left < right){
            int mid = left + (right - left)/2;
            
            // case (3)
            if(mid != 0 && nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]){
                return mid;
            }
            
            // case (2)
            if(nums[mid] > nums[mid + 1]){
                right = mid;
            }
            // case (1), (3);
            else{
                left = mid + 1;
            }
        }
        
        // case (1), (2)
        return left;
    }
	
    public int findPeakElement_old(int[] nums) {
        int beg = 0, end = nums.length - 1;
        
        while(beg < end - 1){
            int mid = beg + (end - beg)/2;
            
            //checking if this is a peak
            if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]){
                return mid;
            }
            
            //checking left condition
            if(nums[mid] > nums[mid+1]){
                end = mid - 1;
            }
            
            //checking right condition
            else {
                beg = mid + 1;
            }
        }
        
        return nums[beg] > nums[end] ? beg : end;
    }
}
