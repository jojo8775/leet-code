package interview.leetcode.prob;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
 

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
Accepted
552,079
Submissions
871,978
 * 
 * Nov 18, 2024 - 9:25:38 PM
 * Jojo 
 */
public class LongestPalindromicSubsequence {
	public int longestPalindromeSubseq(String s) {
        //return topDown(s);
        return bottomUp(s);
    }
    
    private int bottomUp(String s){
        int len = s.length();
        
        int[][] dp = new int[len][len];
        
        for(int i=0; i<len; i++){
            dp[i][i] = 1;
        }
        
        for(int k=2; k<=len; k++){
            for(int i=0, j=k-1; j<len; i++, j++){
                
                if(s.charAt(i) == s.charAt(j)){
                    if(k == 2){
                        dp[i][j] = 2;
                    }
                    else{
                        dp[i][j] = 2 + dp[i+1][j-1];    
                    }
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        
        return dp[0][len-1];
    }
    
    // "bbbab"
    
    private int bottomUp_1(String s){
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
    
    private int topDown(String s) {
        // state
        Integer[][] memo = new Integer[s.length()][s.length()];
        
        return dp(s, 0, s.length() - 1, memo);
    }
    
    private int dp(String s, int i, int j, Integer[][] memo){
        // base case:
        if(i > j){
            return 0;
        }
        
        // base case: every single character is a palindrome
        if(i == j){
            return 1;
        }
        
        if(memo[i][j] != null){
            return memo[i][j];
        }
        
        // relation:
        if(s.charAt(i) == s.charAt(j)){
            return memo[i][j] = 2 + dp(s, i + 1, j - 1, memo);
        }
        
        // relation:
        return memo[i][j] = Math.max(dp(s, i + 1, j, memo), dp(s, i, j-1, memo));
    }
    
    /*
    private int topDown(String s){
        
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
    */
}
