package interview.leetcode.prob;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find
 * the one that is missing from the array.
 * 
 * For example, Given nums = [0, 1, 3] return 2.
 * 
 * Note: Your algorithm should run in linear runtime complexity. Could you
 * implement it using only constant extra space complexity?
 * 
 * @author jojo
 *
 */
public class MissingNumber {
	public int missingNumber(int[] nums) {
        //bucket sort
        for(int i=0; i<nums.length; i++){
            while(nums[i] != i){
                //if nums[i] is out of range
                if(nums[i] < 0 || nums[i] >= nums.length){
                    break;
                }
                
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        
        //find the first non-negative number
        for(int i=0; i<nums.length; i++){
            if(nums[i] != i){
                return i;
            }
        }
        
        //no missing number found
        return nums.length;
    }
}
