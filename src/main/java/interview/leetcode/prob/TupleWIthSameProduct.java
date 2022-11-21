package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.

 

Example 1:

Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
Example 2:

Input: nums = [1,2,4,5,10]
Output: 16
Explanation: There are 16 valid tuples:
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 104
All elements in nums are distinct.
Accepted
21,794
Submissions
35,852
 * @author jojo
 * Nov 21, 2022 2:07:29 PM
 */
public class TupleWIthSameProduct {
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int len = nums.length;
        
        int result = 0;
        
        for(int i=0; i<len-1; i++){
            for(int j=i+1; j < len; j++){
                int key = nums[i] * nums[j];
        
                // multiplying with 8 because for each tuple there are 8 valid combination 
                // 1: [a,b] [c,d]
                // 2: [a,b] [d,c]
                // 3: [b,a] [c,d]
                // 4: [b,a] [d,c]
                // 5: [c,d] [a,b]
                // 6: [c,d] [b,a]
                // 7: [d,c] [a,b]
                // 8: [d,c] [b,a]
                result += (8 * (map.getOrDefault(key, 0)));
                
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        
        return result;
    }
}
