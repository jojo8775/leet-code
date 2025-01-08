package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two 0-indexed integer arrays nums1 and nums2 of length n.

A range [l, r] (inclusive) where 0 <= l <= r < n is balanced if:

For every i in the range [l, r], you pick either nums1[i] or nums2[i].
The sum of the numbers you pick from nums1 equals to the sum of the numbers you pick from nums2 (the sum is considered to be 0 if you pick no numbers from an array).
Two balanced ranges from [l1, r1] and [l2, r2] are considered to be different if at least one of the following is true:

l1 != l2
r1 != r2
nums1[i] is picked in the first range, and nums2[i] is picked in the second range or vice versa for at least one i.
Return the number of different ranges that are balanced. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: nums1 = [1,2,5], nums2 = [2,6,3]
Output: 3
Explanation: The balanced ranges are:
- [0, 1] where we choose nums2[0], and nums1[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 2 = 2.
- [0, 2] where we choose nums1[0], nums2[1], and nums1[2].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 1 + 5 = 6.
- [0, 2] where we choose nums1[0], nums1[1], and nums2[2].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 1 + 2 = 3.
Note that the second and third balanced ranges are different.
In the second balanced range, we choose nums2[1] and in the third balanced range, we choose nums1[1].
Example 2:

Input: nums1 = [0,1], nums2 = [1,0]
Output: 4
Explanation: The balanced ranges are:
- [0, 0] where we choose nums1[0].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 0 = 0.
- [1, 1] where we choose nums2[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 0 = 0.
- [0, 1] where we choose nums1[0] and nums2[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 0 = 0.
- [0, 1] where we choose nums2[0] and nums1[1].
  The sum of the numbers chosen from nums1 equals the sum of the numbers chosen from nums2: 1 = 1.
 

Constraints:

n == nums1.length == nums2.length
1 <= n <= 100
0 <= nums1[i], nums2[i] <= 100
Seen this question in a real interview before?
1/5
Yes
No
Accepted
1.2K
Submissions
2.3K
Acceptance Rate
52.2%
 * Jan 6, 2025 - 10:41:42 PM
 * Jojo 
 */
public class ChooseNumbersFromTwoArraysInRange {
	public int countSubranges(int[] nums1, int[] nums2) {
        int mod = (int)1e9 + 7;

        Map<Integer, Integer> dp1 = new HashMap<>();

        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            // this is used to pick num1[i] and num2[i] and use it to 
            // prev...num2[i-1] - num1[i]
            // prev...num1[i-1] - num2[1]
            Map<Integer, Integer> dp2 = new HashMap<>();

            dp2.put(nums1[i], dp2.getOrDefault(nums1[i], 0) + 1);

            // -ve so that it can be deducted from prev num1[]
            dp2.put(-nums2[i], dp2.getOrDefault(-nums2[i], 0) + 1);

            for (var key : dp1.keySet()) {
                int val = dp1.get(key);

                // picks nums1[i]
                dp2.put(key + nums1[i], (dp2.getOrDefault(key + nums1[i], 0) + val) % mod);
                // picks -nums2[i]
                dp2.put(key - nums2[i], (dp2.getOrDefault(key - nums2[i], 0) + val) % mod);
            }

            // note: the count is from dp2 and not from dp1.
            count = (count + dp2.getOrDefault(0, 0)) % mod;

            // propagating the state.
            dp1 = dp2;
        }
        
        return count;
    }
}
