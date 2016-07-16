package interview.leetcode.prob;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.


 * @author jojo
 *
 */
public class MinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
    	if(nums.length == 1){
    		return nums[0];
    	}
    	
    	int beg=0, end=nums.length - 1;
    	
    	while(beg < end){
    		int mid = beg + (end - beg)/2;
    		
    		if(nums[mid] < nums[end]){
    			end = mid;
    		}
    		else if(nums[mid] > nums[end]){
				beg = mid + 1;
    		}
    		//check if there is a duplicate
    		else{
    			end --;
    		}
    	}
    	
    	return nums[beg];
    }
    
    public static void main(String[] args){
    	int[] nums = {1,2,2,2,2,0};
    	System.out.println(new MinimumInRotatedSortedArrayII().findMin(nums));
    }
}
