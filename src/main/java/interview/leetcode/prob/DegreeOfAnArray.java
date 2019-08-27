package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
 * @author jojo
 * Aug 26, 2019 11:25:47 PM
 */
public class DegreeOfAnArray {
	public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();

        int max = -1, min = Integer.MAX_VALUE;
        
        for(int i=0; i<nums.length; i++){
            int[] val = map.get(nums[i]);
            
            if(val == null){
                val = new int[]{0, i, 0};
                map.put(nums[i], val);
            }
            
            val[0]++;
            val[2] = i;
            
            if(max < val[0]){
                max = val[0];
                min = val[2] - val[1] + 1;
            }
            else if(max == val[0]){
                min = Math.min(min, val[2] - val[1] + 1);
            }
        }
        
        return min;
    }
}
