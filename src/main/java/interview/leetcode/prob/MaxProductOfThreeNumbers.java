package interview.leetcode.prob;

/**
 * Given an integer array, find three numbers whose product is maximum and
 * output the maximum product.
 * 
 * Example 1: Input: [1,2,3] Output: 6 Example 2: Input: [1,2,3,4] Output: 24
 * Note: The length of the given array will be in range [3,104] and all elements
 * are in the range [-1000, 1000]. Multiplication of any three numbers in the
 * input won't exceed the range of 32-bit signed integer.
 * 
 * @author jojo Jun 25, 201711:31:46 AM
 */
public class MaxProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        Integer num1 = null, num2 = null, num3 = null;
        
        int neg1 = 0, neg2 = 0;
        
        for(int i=0; i<nums.length; i++){
            if(num1 == null || num1 <= nums[i]){
                num3 = num2;
                num2 = num1;
                num1 = nums[i];
            }
            else if(num2 == null || num2 <= nums[i]){
                num3 = num2;
                num2 = nums[i];
            }
            else if(num3 == null || num3 <= nums[i]){
                num3 = nums[i];
            }
            
            if(nums[i] < 0){
                if(neg1 >= nums[i]){
                    neg2 = neg1;
                    neg1 = nums[i];
                }
                else if(neg2 >= nums[i]){
                    neg2 = nums[i];
                }
            }
        }
        
        return Math.max(num1 * num2 * num3, num1 * neg1 * neg2);
    }
}
