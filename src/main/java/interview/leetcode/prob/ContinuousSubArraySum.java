package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 * @author jojo
 *
 */
public class ContinuousSubArraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        
        // idea is x + n*k mod k = x
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        // this is done to handle input like nums = [0,0], k = 0
        map.put(0, -1);
        
        int sumSoFar = 0;
        for(int i=0; i<nums.length; i++){
            sumSoFar += nums[i];
            
            // to avoid /0 
            if(k != 0){
                sumSoFar %= k;
            }
            
            // if there is a same mod and the space between is more than 1 then result is found
            if(map.containsKey(sumSoFar)){
                int prevIdx = map.get(sumSoFar);
                if(i - prevIdx > 1){
                    return true;
                }
            }
            else{
                map.put(sumSoFar, i);
            }
        }
        
        return false;
    }
}
