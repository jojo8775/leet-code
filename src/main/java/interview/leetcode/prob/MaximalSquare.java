package interview.leetcode.prob;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4.
 * @author jojo
 *
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        
        int[][] grid = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(i==0 || j==0){
                    grid[i][j] = matrix[i][j] - '0';
                }
                else if(matrix[i][j] != '0'){
                    grid[i][j] = Math.min(grid[i-1][j-1], Math.min(grid[i-1][j], grid[i][j-1])) + 1;
                }
                
                maxArea = Math.max(maxArea, grid[i][j]);
            }
        }
        
        return maxArea * maxArea;
    }
}
