package interview.leetcode.prob.paid;

/**
 * A sequence is special if it consists of a positive number of 0s, followed by a positive number of 1s, then a positive number of 2s.

For example, [0,1,2] and [0,0,1,1,1,2] are special.
In contrast, [2,1,0], [1], and [0,1,2,0] are not special.
Given an array nums (consisting of only integers 0, 1, and 2), return the number of different subsequences that are special. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of an array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements. Two subsequences are different if the set of indices chosen are different.

 

Example 1:

Input: nums = [0,1,2,2]
Output: 3
Explanation: The special subsequences are [0,1,2,2], [0,1,2,2], and [0,1,2,2].
Example 2:

Input: nums = [2,2,0,0]
Output: 0
Explanation: There are no special subsequences in [2,2,0,0].
Example 3:

Input: nums = [0,1,2,0,1,2]
Output: 7
Explanation: The special subsequences are:
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 2
Accepted
3,359
Submissions
6,816
 * @author jojo
 * Aug 2, 2021  5:12:11 PM
 */
public class CountNumberOfSpecialSubsequences {
	public int countSpecialSubsequences(int[] nums) {
        // dp[0] contains all subsequence of 0s 
        // dp[1] contains all subsequence of 1s after 0s
        // dp[2] contains all subsequence of 2s after 1s
        long[] dp = new long[3];
        
        long mod = (long)(1e9 + 7);
        for(int n : nums){
            // if n == 0 then it is d[0] += dp[0] + 1 since 0 standalone can represent a valid subsequence 
            // if n == 1 then it is d[1] += d[1] + d[0]; since the new 1 can be appended on the existing subsequece ending in 1 and the existing 
            // subsequence ending in 0
            // simillarly, if n == 2 then it is d[2] += d[2] + d[1];
            dp[n] = ((dp[n] + dp[n]) % mod + (n > 0 ? dp[n-1] : 1)) % mod;
        }
        
        return (int) dp[2];
    }
}
