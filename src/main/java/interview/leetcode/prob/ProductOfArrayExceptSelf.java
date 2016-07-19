package interview.leetcode.prob;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].
 * @author jojo
 *
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        for(int i=1; i<nums.length; i++){
            left[i] = left[i-1] * nums[i-1];
        }
        
        int[] right = new int[nums.length];
        right[nums.length-1] = 1;
        for(int i=nums.length-2; i>=0; i--){
            right[i] = right[i+1]*nums[i+1];
        }
        
        int[] result = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            result[i] = left[i]*right[i];
        }
        
        return result;
    }
    
    public int[] productExceptSelf_2(int[] nums) {
    	int[] result = new int[nums.length];
    	result[0] = 1;
    	for(int i=1; i<nums.length; i++){
    		result[i] = result[i-1]*nums[i-1];
    	}
    	
    	int right = 1;
    	for(int i=result.length - 1; i>=0; i--){
    		result[i] *= right;
    		right *= nums[i];
    	}
    	
    	return result;
    }
}
