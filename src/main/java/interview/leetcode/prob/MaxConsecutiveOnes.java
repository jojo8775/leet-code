package interview.leetcode.prob;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
 * @author jojo
 *Mar 11, 201711:59:26 PM
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int beg = 0, end = 0, result = 0, len = nums.length;
        
        while(end < len){
            if(nums[end] != 1){
                result = Math.max(result, end - beg);
                
                // end + 1 because current end represents 0 and we are assuming end + 1 will represent 1
                beg = end + 1;
            }
            
            end++;
        }
        
        return Math.max(result, end - beg);
    }
}
