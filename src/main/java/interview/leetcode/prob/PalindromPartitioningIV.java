package interview.leetcode.prob;

/**
 * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.

 

Example 1:

Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
Example 2:

Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.
 

Constraints:

3 <= s.length <= 2000
s​​​​​​ consists only of lowercase English letters.
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
32,858/72.6K
Acceptance Rate
45.2%
 * 
 * chiranjeebnandy
 * Apr 13, 2026  2026  11:36:34 PM
 */
public class PalindromPartitioningIV {
	 public boolean checkPartitioning(String s) {
	        int len = s.length();
	            
	        // Step 1: Precompute the palindrome table (Same logic as your previous code)
	        boolean[][] dp = new boolean[len][len];
	        
	        // Filling the DP table for all possible palindromic substrings
	        for (int i = 0; i < len; i++) {
	            for (int j = 0; j <= i; j++) {
	                if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || dp[j + 1][i - 1])) {
	                    dp[j][i] = true;
	                }
	            }
	        }

	        // Step 2: Try every possible way to split the string into 3 parts
	        // We need 2 split points: i and j
	        // Part 1: [0, i]
	        // Part 2: [i + 1, j]
	        // Part 3: [j + 1, len - 1]
	        for (int i = 0; i < len - 2; i++) {
	            for (int j = i + 1; j < len - 1; j++) {
	                // Check if all three parts are palindromes using our DP table
	                if (dp[0][i] && dp[i + 1][j] && dp[j + 1][len - 1]) {
	                    return true;
	                }
	            }
	        }

	        return false;
	    }
}
