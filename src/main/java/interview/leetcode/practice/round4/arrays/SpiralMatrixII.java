package interview.leetcode.practice.round4.arrays;

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int start = 0, end = n - 1, count = 1;

        int[][] dp = new int[n][n];

        while (start <= end) {
            for (int i = start, j = 0; i <= end; i++, j++) {
                dp[start][start + j] = count++;
            }

            for (int i = start + 1, j = 1; i <= end; i++, j++) {
                dp[start + j][end] = count++;
            }

            for (int i = start + 1, j = 1; i <= end; i++, j++) {
                dp[end][end - j] = count++;
            }

            for (int i = start + 1, j = 1; i < end; i++, j++) {
                dp[end - j][start] = count++;
            }

            start++;
            end--;
        }

        return dp;
    }
}
