package interview.leetcode.prob;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You
 * may assume that the maximum length of s is 1000.
 * 
 * Example 1: Input:
 * 
 * "bbbab" Output: 4 One possible longest palindromic subsequence is "bbbb".
 * Example 2: Input:
 * 
 * "cbbd" Output: 2 One possible longest palindromic subsequence is "bb".
 * 
 * @author jojo Mar 20, 201711:48:47 PM
 */
public class LongestPalindromSequence {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        // bottom up approach
        for (int i = 0; i < len; i++) {
            for (int j = 0; j + i < len; j++) {
                int k = j + i;

                // all single characters are palindrom
                if (k == j) {
                    dp[j][k] = 1;
                }
                // if first and last are same then 2 + mid
                else if (s.charAt(j) == s.charAt(k)) {
                    dp[j][k] = 2 + dp[j + 1][k - 1];
                }
                // if they are diff then max of substring without first and
                // substring without last
                else {
                    dp[j][k] = Math.max(dp[j + 1][k], dp[j][k - 1]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
