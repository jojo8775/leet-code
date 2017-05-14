package interview.leetcode.prob;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 * 
 * Example: Input: [[0,1,1,0], [0,1,1,0], [0,0,0,1]] Output: 3 Hint: The number
 * of elements in the given matrix will not exceed 10,000.
 * 
 * @author jojo May 14, 20172:21:02 AM
 */
public class LongestLineOfConsecutive1Matrix {
    public int longestLine(int[][] M) {
        if (M.length == 0 || M[0].length == 0) {
            return 0;
        }

        // 4 to represent horizontal, vertical, diagonal, and anti diagonal
        int[][][] dp = new int[M.length][M[0].length][4];
        int maxLength = 0;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = 1;
                }

                if (i > 0) {
                    dp[i][j][0] += dp[i - 1][j][0]; // k = 0 represents vertical
                }

                if (j > 0) {
                    dp[i][j][1] += dp[i][j - 1][1]; // k = 1 represents
                                                    // horizontal
                }

                if (i > 0 && j > 0) {
                    dp[i][j][2] += dp[i - 1][j - 1][2]; // k = 2 represents
                                                        // diagonal
                }

                if (i > 0 && j < M[0].length - 1) {
                    dp[i][j][3] += dp[i - 1][j + 1][3]; // k = 3 represents anti
                                                        // diagonal
                }

                maxLength = Math.max(maxLength,
                        Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
            }
        }

        return maxLength;
    }
}
