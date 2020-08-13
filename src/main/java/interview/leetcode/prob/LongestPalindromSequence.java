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
	public int longestPalindromeSubseq_recursion(String s) {
        Integer[][] memo = new Integer[s.length()][s.length()];
        return recurse(s, 0, s.length() - 1, memo);
    }
    
    private int recurse(String str, int i, int j, Integer[][] memo){
        if(i > j){
            return 0;
        }
        
        if(i == j){
            return 1;
        }
        
        if(memo[i][j] != null){
            return memo[i][j];
        }
        
        if(str.charAt(i) == str.charAt(j)){
            
            memo[i][j] = 2 + recurse(str, i+1, j-1, memo);
        }
        else{
            int val1 = recurse(str, i+1, j, memo);
            int val2 = recurse(str, i, j-1, memo);
            
            memo[i][j] = Math.max(val1, val2);
        }
        
        return memo[i][j];
    }
	
        
    // DP approach 1
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
