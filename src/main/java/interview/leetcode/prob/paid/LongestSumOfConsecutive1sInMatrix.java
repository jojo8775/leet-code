package interview.leetcode.prob.paid;

public class LongestSumOfConsecutive1sInMatrix {
    public int longestLine(int[][] M) {
        if (M.length == 0 || M[0].length == 0) {
            return 0;
        }

        int[][][] dp = new int[M.length][M[0].length][4];

        int maxResult = 0;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {

                if (M[i][j] == 1) {
                    dp[i][j][0] = (i == 0 ? 0 : dp[i - 1][j][0]) + 1;
                    dp[i][j][1] = (j == 0 ? 0 : dp[i][j - 1][1]) + 1;
                    dp[i][j][2] = ((i > 0 && j > 0) ? dp[i - 1][j - 1][2] : 0) + 1;
                    dp[i][j][3] = ((i > 0 && j < M[0].length - 1) ? dp[i - 1][j + 1][3] : 0) + 1;
                    

                    maxResult = Math.max(maxResult, dp[i][j][0]);
                    maxResult = Math.max(maxResult, dp[i][j][1]);
                    maxResult = Math.max(maxResult, dp[i][j][2]);
                    maxResult = Math.max(maxResult, dp[i][j][3]);
                }
                // else if(i==0 && M[i][j] == 1){

                // }

                // if(i==0 || j==0){

                // }
                // if(i>0 && M[i][j] == 1){
                // dp[i][j][0] = dp[i-1][j][0] + 1;
                // maxResult = Math.max(maxResult, dp[i][j][0]);
                // }
                // if(j>0 && M[i][j] == 1){
                // dp[i][j][1] = dp[i][j-1][1] + 1;
                // maxResult = Math.max(maxResult, dp[i][j][1]);
                // }
                // if(i>0 && j>0 && M[i][j] == 1){
                // dp[i][j][2] = dp[i-1][j-1][1] + 1;
                // maxResult = Math.max(maxResult, dp[i][j][2]);
                // }
            }
        }

        return maxResult;
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 0, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1, 1, 0, 0, 1 }, { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 1, 0, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 0, 1, 1, 1, 1 } };

        System.out.println(new LongestSumOfConsecutive1sInMatrix().longestLine(arr));
    }
}
