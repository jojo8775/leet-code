package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.
 

Example 1:

Input: s = "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".
Example 3:

Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters.
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
52,410/118.8K
Acceptance Rate
44.1%

 * 
 * chiranjeebnandy
 * Jun 6, 2026  2026  11:55:59 AM
 */
public class DistinctSequenceII {
	public int distinctSubseqII(String s) {
        int mod = (int)(1e9 + 7);
        
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // base case: empty set is a sequeces         

        int[] lastSeenIdx = new int[26];
        Arrays.fill(lastSeenIdx, -1);

        for(int i=1; i<= s.length(); i++){
            // * 2 because in a subset if a new char is added. it contributes twice 
            // e.g. if 3 is added to [], [1], [2], [1,2] --> 4 items
            // end result            [], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3] --> 8 items 
            dp[i] = (dp[i - 1] * 2) % mod;

            char ch = s.charAt(i -1);
            int charIdx = (int) (ch - 'a');
            
            if(lastSeenIdx[charIdx] != -1){
                // when the char is seem, we need to remove the duplicate 
                // e.g when to 2 is added to [], [1], [2], [1,2] --> index of 2 is 1 (0 indexed)
                // result                    [], [1], [2], [1,2], [2], [1,2], [2,2], [1,2,2] 
                // there are two duplicates                   *    *
                // + mod because the original result is already mod and reduced, the substraction 
                // might move it to negative. Adding mod to bring it back to positive.
                int prevIdx = lastSeenIdx[charIdx];

                dp[i] = (dp[i] - dp[prevIdx] + mod) % mod;
            }

            lastSeenIdx[charIdx] = i-1;
        }

        return (dp[s.length()] - 1 + mod) % mod;
    }



    public int distinctSubseqII_1(String s) {
        //int MOD = 1_000_000_007;
        int MOD = (int)(1e9 + 7);
        int n = s.length();
        
        // dp[i] will store the number of distinct subsequences using the first i characters
        int[] dp = new int[n + 1];
        
        // Base case: an empty string has 1 subsequence (the empty string itself)
        dp[0] = 1;
        
        // Track the last seen index of each character ('a' through 'z')
        int[] lastPos = new int[26];
        Arrays.fill(lastPos, -1);
        
        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int charIndex = ch - 'a';
            
            // Step 1: Double the count from the previous state 
            // (either we don't include ch, or we append ch to all existing subsequences)
            dp[i] = (dp[i - 1] * 2) % MOD;
            
            // Step 2: If we've seen this character before, subtract duplicates
            if (lastPos[charIndex] != -1) {
                int prevIndex = lastPos[charIndex];
                dp[i] = (dp[i] - dp[prevIndex] + MOD) % MOD;
            }
            
            // Step 3: Record the current character's position
            // We use `i - 1` because dp is 1-indexed relative to string positions
            lastPos[charIndex] = i - 1;
        }
        
        // dp[n] includes the empty string, so subtract 1 for "non-empty" subsequences
        return (dp[n] - 1 + MOD) % MOD;
    }
}
