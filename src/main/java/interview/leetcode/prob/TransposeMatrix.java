package interview.leetcode.prob;

/**
 * Given a 2D integer array matrix, return the transpose of matrix.

The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.



 

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]
Example 2:

Input: matrix = [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
-109 <= matrix[i][j] <= 109
Accepted
146,104
Submissions
235,875
 * @author jojo
 * Jun 1, 2022 9:10:21 PM
 */
public class TransposeMatrix {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        int[][] result = new int[n][m];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                result[j][i] = matrix[i][j];
            }
        }
        
        return result;
    }
}
