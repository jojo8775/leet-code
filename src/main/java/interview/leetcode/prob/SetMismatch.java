package interview.leetcode.prob;

/**
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.
 * @author jojo
 *
 */
public class SetMismatch {
	public int[] findErrorNums_2nd(int[] nums) {
        int[] result = new int[2];
        
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                result[0] = nums[i];
                result[1] = i + 1;
                break;
            }
        }
        
        return result;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
	
	
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for(int i=0; i<nums.length; i++){
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] < 0){
                result[0] = idx + 1;
            }
            else {
                nums[idx] *= -1;
            }
        }
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                result[1] = i + 1;
                break;
            }
        }
        
        return result;
    }
}
