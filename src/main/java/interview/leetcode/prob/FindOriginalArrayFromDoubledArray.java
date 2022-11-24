package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

 

Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.
 

Constraints:

1 <= changed.length <= 105
0 <= changed[i] <= 105
Accepted
114,248
Submissions
279,284
 * @author jojo
 * Nov 24, 2022 1:14:36 PM
 */
public class FindOriginalArrayFromDoubledArray {
	public int[] findOriginalArray(int[] changed) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : changed){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        Arrays.sort(changed);
        
        int len = changed.length;
        int[] result = new int[len/2];
        int idx = 0;
        
        if((map.containsKey(0) && map.get(0) % 2 != 0) || len % 2 != 0){
            return new int[]{};
        }
        
        for(int n : changed){
            if(n % 2 == 0 && map.containsKey(n/2)){
                if(idx == result.length){
                   idx = -1;
                    break;
                }
                
                int val1 = map.get(n);
                val1--;
                if(val1 == 0){
                    map.remove(n);
                }
                else{
                    map.put(n, val1);
                }
                
                
                int val2 = map.get(n/2);
                val2--;
                if(val2 == 0){
                    map.remove(n/2);
                }
                else{
                    map.put(n/2, val2);
                }
                
                result[idx++] = n/2;
            }
        }
        
        return idx == result.length ? result : new int[]{};
    }
}
