package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

 

Example 1:


Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.
Example 2:


Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.
Example 3:

Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m * n <= 105
matrix[i][j] is either 0 or 1.
Accepted
21,093
Submissions
32,227
 * @author jojo
 * Nov 26, 2023 3:30:49 PM
 */
public class LargestSubmatrixWithRearrangements {
	// m*nlongn
    public int largestSubmatrix_0(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        int[][] grid = new int[m][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i == 0){
                    grid[i][j] = matrix[i][j];
                }
                else if(matrix[i][j] > 0){
                    grid[i][j] = matrix[i][j] + grid[i-1][j];
                }
            }
        }
        
        int result = 0;
        for(int i=0; i<m; i++){
            Arrays.sort(grid[i]);
            
            for(int j=n-1; j>=0; j--){
                if(grid[i][j] == 0){
                    break;
                }
                
                result = Math.max(result, grid[i][j] * (n-j));
            }
        }
        
        return result;
    }
    
    // m*n
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        List<int[]> prevHeights = new ArrayList<>();
        
        int result = 0;
        
        for(int row = 0; row < m; row++){
            
            List<int[]> curHeights = new ArrayList<>();
            
            boolean[] seen = new boolean[n];
            
            for(int[] entry : prevHeights){
                int col = entry[1], height = entry[0];
                
                if(matrix[row][col] == 1){
                    curHeights.add(new int[]{height + 1, col});
                    seen[col] = true;
                }
            }
            
            for(int col=0; col < n; col++){
                if(!seen[col] && matrix[row][col] == 1){
                    curHeights.add(new int[]{1, col});
                }
            }
            
            for(int i=0; i<curHeights.size(); i++){
                result = Math.max(result, curHeights.get(i)[0] * (i+1));
            }
            
            prevHeights = curHeights;
        }
        
        return result;
    }
}
