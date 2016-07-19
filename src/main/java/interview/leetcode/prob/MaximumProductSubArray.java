package interview.leetcode.prob;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 * @author jojo
 *
 */
public class MaximumProductSubArray {
    public int maxProduct(int[] nums) {
        //to store min possible entry
        int[] min = new int[nums.length];
        min[0] = nums[0];
        
        //to store max possible entry
        int[] max = new int[nums.length];
        max[0] = nums[0];
        
        int result = max[0];
        
        for(int i=1; i<nums.length; i++){
            if(nums[i]>0){
                //max of current or prev max*current
                max[i] = Math.max(nums[i], nums[i] * max[i-1]);
                //min of current or prev min*curent
                min[i] = Math.min(nums[i], nums[i] * min[i-1]);
            }
            else{
                //max of current or prev min * current
                max[i] = Math.max(nums[i], nums[i] * min[i-1]);
                //min of current or prev max * current
                min[i] = Math.min(nums[i], nums[i] * max[i-1]);
            }
            
            result = Math.max(result, max[i]);
        }
        
        return result;
    }
    
    public static void main(String[] args){
    	int[] arr = {-2,3,-4};
    	System.out.println(new MaximumProductSubArray().maxProduct(arr));
    }
}
