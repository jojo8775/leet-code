package interview.leetcode.prob;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
Accepted
330,219
Submissions
746,765
 * @author jojo
 * Nov 25, 2022 12:52:14 PM
 */
public class OneMatrix {
	public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, INF = m + n; // The distance of cells is up to (M+N)
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0){
                  continue;  
                } 
                
                int top = INF, left = INF;
                
                if (r - 1 >= 0) {
                    top = matrix[r - 1][c];
                }
                
                if (c - 1 >= 0) {
                    left = matrix[r][c - 1];
                }
                
                matrix[r][c] = Math.min(top, left) + 1;
            }
        }
        
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (matrix[r][c] == 0){
                    continue;  
                } 
                
                int bottom = INF, right = INF;
                
                if (r + 1 < m) {
                    bottom = matrix[r + 1][c];
                }
                
                if (c + 1 < n) {
                    right = matrix[r][c + 1];
                }
                
                matrix[r][c] = Math.min(matrix[r][c], Math.min(bottom, right) + 1);
            }
        }
        
        return matrix;
    }
}
