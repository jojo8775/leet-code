package interview.leetcode.prob;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
 * @author jojo
 *
 */
public class FindTheDuplicate {
    public int findDuplicate(int[] nums) {
        //taking the range beg = 1 and end = n
        int beg = 1, end = nums.length - 1;
        
        while(beg < end){
            //finding the middle number
            int mid = beg + (end - beg)/2;
            
            //scanning the entire array for number less than mid
            int count = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i] <= mid){
                    count++;
                }
            }
            
            // this means there is no repeate on the left side
            if(count <= mid){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return beg;
    }
}
