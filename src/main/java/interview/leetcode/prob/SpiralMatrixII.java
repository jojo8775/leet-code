package interview.leetcode.prob;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 * @author jojo
 *
 */
public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
        if(n<0){
            return null;
        }
        
        int[][] grid = new int[n][n];
        
        if(n == 1){
            grid[0][0] = 1;
            return grid;
        }
        
        int count=1, top=0, bottom=n-1, left=0, right=n-1;
        
        while(top<=bottom || left<=right){
            //going left
            for(int i=left; i<=right; i++){
                grid[top][i] = count++;
            }
            
            //going down
            for(int i=top+1; i<=bottom; i++){
                grid[i][right] = count++;
            }
            
            //going right
            for(int i=right-1; i>=left && right != left; i--){
                grid[bottom][i] = count++;
            }
            
            //going up
            for(int i=bottom-1; i>top && top!=bottom; i--){
                grid[i][left] = count++;
            }
            
            top++;
            right--;
            bottom--;
            left++;
        }
        
        return grid;
    }	
}
