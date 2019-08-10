package interview.leetcode.prob;

/**
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.

 

Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.
 

Note:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500

 * @author jojo
 * Jun 29, 2019 5:24:02 PM
 */
public class LongestRepeatingSubstring {
	public int longestRepeatingSubstring(String S) {
        int len = S.length(), result = 0;
        if(len == 0){
            return result;
        }
        
        // stores the max repetition length for a substring.
        int[] dp = new int[len + 1];
        for(int i=1; i<=len; i++){
            for(int j=i-1; j>=1; j--){
                if(S.charAt(i-1) == S.charAt(j-1)){
                    dp[j] = dp[j-1] + 1;
                }
                else{
                    dp[j] = 0;
                }
                
                result = Math.max(result, dp[j]);
            }
        }
        
        return result;
    }
}
