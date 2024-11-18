package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if it there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i], k <= 109
Accepted
23,507
Submissions
45,227
 * 
 * Nov 17, 2024 - 12:53:19 AM
 * Jojo 
 */
public class CountTheNumberOfGoodSubarrays {
    public long countGood(int[] nums, int k) {
        long result = 0L;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0, j=0; i<nums.length; i++){
            int count = map.getOrDefault(nums[i], 0);
            k -= count;
            
            map.put(nums[i], count + 1);
            
            while(k <= 0){
                int val = map.get(nums[j]);
                val -= 1;
                
                // reducing one freq count in the map
                map.put(nums[j], val);
                
                //adding back initial val - 1 becuase 
                // 2 count (1) makes 1 + 2 pairs. In other words 3 1s makes 3 pairs. 
                // during parsing when we foudn the third 1, map had val=2. So the result was 1+2
                // now that we are taking 1 away, we need to remove 2.
                // this is becuase 2 1s can only make 1 pair 
                k += val;
                
                j++;
            }
            
            // adding j here because it is gauranteed that from j -- i the array will have atleast k pairs 
            // from 0 -- j it have only make k greater or equal. 
            result += (long)j;
        }
        
        return result;
    }
}
