package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
Example 2:

Input: nums = [5], k = 9
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
Accepted
135,825
Submissions
253,040
 * @author jojo
 * Jan 18, 2023 8:17:41 PM
 */
public class SubarraySumsDivisibleByK {
	public int subarraysDivByK_adv(int[] nums, int k) {
        int n = nums.length;
        int prefixMod = 0, result = 0;

        // There are k mod groups 0...k-1.
        int[] modGroups = new int[k];
        modGroups[0] = 1;

        for (int num: nums) {
            // Take modulo twice to avoid negative remainders.
            prefixMod = (prefixMod + num % k + k) % k;
            // Add the count of subarrays that have the same remainder as the current
            // one to cancel out the remainders.
            result += modGroups[prefixMod];
            modGroups[prefixMod]++;
        }

        return result;   
    }
	
	public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        int sum = 0;
        int result = 0;
        
        for(int n : nums){
            sum += ((n % k) + k); // + k to makes sure the negative remainder is transformed into positive
                                  // this is because -4 and 4 both are divisible by 2.
            sum %= k;
            
            result += map.getOrDefault(sum, 0);
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
}
