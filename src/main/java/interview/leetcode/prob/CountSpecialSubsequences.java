package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array nums consisting of positive integers.

A special subsequence is defined as a subsequence of length 4, represented by indices (p, q, r, s), where p < q < r < s. This subsequence must satisfy the following conditions:

nums[p] * nums[r] == nums[q] * nums[s]
There must be at least one element between each pair of indices. In other words, q - p > 1, r - q > 1 and s - r > 1.
A subsequence is a sequence derived from the array by deleting zero or more elements without changing the order of the remaining elements.

Return the number of different special subsequences in nums.

 

Example 1:

Input: nums = [1,2,3,4,3,6,1]

Output: 1

Explanation:

There is one special subsequence in nums.

(p, q, r, s) = (0, 2, 4, 6):
This corresponds to elements (1, 3, 3, 1).
nums[p] * nums[r] = nums[0] * nums[4] = 1 * 3 = 3
nums[q] * nums[s] = nums[2] * nums[6] = 3 * 1 = 3
Example 2:

Input: nums = [3,4,3,4,3,4,3,4]

Output: 3

Explanation:

There are three special subsequences in nums.

(p, q, r, s) = (0, 2, 4, 6):
This corresponds to elements (3, 3, 3, 3).
nums[p] * nums[r] = nums[0] * nums[4] = 3 * 3 = 9
nums[q] * nums[s] = nums[2] * nums[6] = 3 * 3 = 9
(p, q, r, s) = (1, 3, 5, 7):
This corresponds to elements (4, 4, 4, 4).
nums[p] * nums[r] = nums[1] * nums[5] = 4 * 4 = 16
nums[q] * nums[s] = nums[3] * nums[7] = 4 * 4 = 16
(p, q, r, s) = (0, 2, 5, 7):
This corresponds to elements (3, 3, 4, 4).
nums[p] * nums[r] = nums[0] * nums[5] = 3 * 4 = 12
nums[q] * nums[s] = nums[2] * nums[7] = 3 * 4 = 12
 

Constraints:

7 <= nums.length <= 1000
1 <= nums[i] <= 1000
Seen this question in a real interview before?
1/5
Yes
No
Accepted
1.3K
Submissions
15.3K
Acceptance Rate
8.8%
 * 
 * Dec 29, 2024 - 12:26:13 AM
 * Jojo 
 */
public class CountSpecialSubsequences {
	public long numberOfSubsequences(int[] nums) {
        Map<Double, Integer> map = new HashMap<>();
        int len = nums.length;

        long total = 0L;
        for(int r=4; r < len-2; r++){
            int q = r - 2;
            
            for(int p = 0; p<q-1; p++){
                double key = (1.0 * nums[p]) / nums[q];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            for(int s = r+2; s<len; ++s){
                double key = (1.0 * nums[s]) / nums[r];
                total += map.getOrDefault(key, 0);
            }
        }

        return total;
    }
}
