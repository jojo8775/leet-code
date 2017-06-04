package interview.leetcode.prob;

/**
 * Given two words word1 and word2, find the minimum number of steps required to
 * make word1 and word2 the same, where in each step you can delete one
 * character in either string.
 * 
 * Example 1: Input: "sea", "eat" Output: 2 Explanation: You need one step to
 * make "sea" to "ea" and another step to make "eat" to "ea". Note: The length
 * of given words won't exceed 500. Characters in given words can only be
 * lower-case letters.
 * 
 * @author jojo May 17, 20177:37:42 PM
 */
public class DeleteOperationOfTwoString {
    public int minDistance(String word1, String word2) {
        // 0 represents "" in row and col
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // number of deletion required to achieve "" in word1
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        // number of deletion required to achieve "" in word2
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            char ch = word1.charAt(i - 1);
            for (int j = 1; j < dp[0].length; j++) {
                // if its a match check the sub string of word1(0,i-1) and
                // word2(0,j-1)
                if (ch == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // else take the minimum + 1
                else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // minimum result
        return dp[word1.length()][word2.length()];
    }
}
