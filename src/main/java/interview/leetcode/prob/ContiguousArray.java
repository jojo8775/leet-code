package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * @author jojo
 *Mar 22, 20175:56:15 AM
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        
        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, - 1);
        
        int sum = 0, len = 0;
        for(int i=0; i<nums.length; i++){
            sum = nums[i] == 0 ? sum - 1 : sum + 1;
            
            // idea is if sum = 2 at index 1 and its back to 2 at index 8 then
            // it means sum from 2 to 8 == 0
            if(sumMap.containsKey(sum)){
                len = Math.max(len, i - sumMap.get(sum));
            }
            else{
                sumMap.put(sum, i);
            }
        }
        
        return len;
    }
}
