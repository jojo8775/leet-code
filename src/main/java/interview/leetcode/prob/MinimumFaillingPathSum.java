package interview.leetcode.prob;

/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

 

Example 1:


Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
Example 2:


Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
Accepted
121,470
Submissions
180,361
 * @author jojo
 * Apr 17, 2022 11:19:31 PM
 */
public class MinimumFaillingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        
        for(int j=0; j<n; j++){
            dp[0][j] = matrix[0][j];
        }
        
        for(int i=1; i<m; i++){
            for(int j=0; j<n; j++){
                int beg = j == 0 ? 0 : j-1, end = j == n-1 ? j : j+1;
                
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = beg; k<=end; k++){
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i-1][k]);
                }
            }
        }
        
        int min = dp[m-1][0];
        for(int j=0; j<n; j++){
            min = Math.min(min, dp[m-1][j]);
        }
        
        return min;
    }
}
