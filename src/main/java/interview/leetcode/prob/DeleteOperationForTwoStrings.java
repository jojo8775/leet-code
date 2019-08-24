package interview.leetcode.prob;

/**
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
 * @author jojo
 * Aug 24, 2019 12:50:29 PM
 */
public class DeleteOperationForTwoStrings {
	public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        // this is same as min edit problem
        for(int i=1; i<dp.length; i++){
            dp[i][0] = i;
        }
        
        for(int i=1; i<dp[0].length; i++){
            dp[0][i] = i;
        }
        
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(word1.charAt(i-1) == word2.charAt(j - 1)){
                    // if the characters matched, then just need to do computation on the substring.
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    // min computation after removing last char of w1 or w2 + 1 because the characters are not matching. 
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}
