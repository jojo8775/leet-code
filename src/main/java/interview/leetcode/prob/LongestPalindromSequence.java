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
	public int longestPalindromeSubseq_adv(String s) {
        return topDown(s);
        //return bottomUp(s);
    }
    
    private int bottomUp(String s){
        int len = s.length();
    
        // states
        int[][] dp = new int[len][len];

        for(int i=len-1; i>=0; i--){
            // base case. Every single character is a palindrom
            dp[i][i] = 1;

            for(int j=i+1; j<len; j++){
                // relation: 
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2 + dp[i+1][j-1];
                }
                // relation:
                else{
                    dp[i][j] += Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][len-1];
    }
    
    private int topDown(String s){
        // state
        Integer[][] memo = new Integer[s.length()][s.length()];
        return dp(s, 0, s, s.length() - 1, memo);
    }

    private int dp(String s1, int idx1, String s2, int idx2, Integer[][] memo){
        // base case:
        if(idx1 > idx2){
            return 0;
        }
    
        // base case: every single character is a palindrome
        if(idx1 == idx2){
            return 1;
        }

        if(memo[idx1][idx2] != null){
            return memo[idx1][idx2];
        }

        int val = 0;
        
        // relation:
        if(s1.charAt(idx1) == s2.charAt(idx2)){
            val = 2 + dp(s1, idx1 + 1, s2, idx2 - 1, memo);
        }
        // relation:
        else{
            val = Math.max(dp(s1, idx1, s2, idx2 - 1, memo), dp(s1, idx1 + 1, s2, idx2, memo));
        }

        return memo[idx1][idx2] = val;	
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
