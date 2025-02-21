package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].

Return the total number of bad pairs in nums.

 

Example 1:

Input: nums = [4,1,3,3]
Output: 5
Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
There are a total of 5 bad pairs, so we return 5.
Example 2:

Input: nums = [1,2,3,4,5]
Output: 0
Explanation: There are no bad pairs.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
 * 
 * Feb 8, 2025 - 9:49:45 PM
 * Jojo 
 */
public class CoundNumberOfBadPairs {
	public long countBadPairs(int[] nums) {
        Map<Long, Long> map = new HashMap<>();

        long goodPairCount = 0L;

        for(int i=0; i<nums.length; i++){
            long diff = (long)nums[i] - (long)i;

            // based on the problem 
            // (i - j) != (nums[i] - nums[j])
            // --> this can be written as 
            // i - nums[i] != j - nums[j]
            // --> above condition is for bad number 
            // for good pairs it should be 
            // 1 - nums[i] == j - nums[j] // this is easy to count so counting this instead.
            goodPairCount += map.getOrDefault(diff,0L);
            map.put(diff, map.getOrDefault(diff, 0L) + 1L);
        }

        long len = (long)nums.length;

        // if there are 4 elements in an array, then there are 12 pairs possible 3 * 4 
        // since half of these will be a repeat pair (a,b -> is same as b,a) we are dividing it by 2
        long totalPairs = (len * (len - 1L)) / 2L;

        return totalPairs - goodPairCount;
    }
}
