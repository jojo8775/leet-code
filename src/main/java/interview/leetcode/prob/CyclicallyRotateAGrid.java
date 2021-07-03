package interview.leetcode.prob;

/**
 * You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:



A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:


Return the matrix after applying k cyclic rotations to it.

 

Example 1:


Input: grid = [[40,10],[30,20]], k = 1
Output: [[10,20],[40,30]]
Explanation: The figures above represent the grid at every state.
Example 2:

  
Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
Explanation: The figures above represent the grid at every state.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 50
Both m and n are even integers.
1 <= grid[i][j] <= 5000
1 <= k <= 109


 * @author jojo
 * Jun 29, 2021  11:28:59 PM
 */
public class CyclicallyRotateAGrid {
	public int[][] rotateGrid(int[][] grid, int k) {
        int left = 0, right = grid[0].length - 1, top = 0, bottom = grid.length - 1;
        
        while(top < bottom && left < right){
            int totalElements = 2 * (bottom - top + 1) + 2 * (right - left + 1) - 4;
            int times = k%totalElements;
            
            while(--times >= 0){
                int temp = grid[top][left];
                
                for(int j=left; j < right; j++){
                    grid[top][j] = grid[top][j + 1];
                }
                
                for(int j=top; j < bottom; j++){
                    grid[j][right] = grid[j + 1][right];
                }
                
                for(int j=right; j > left; j--){
                    grid[bottom][j] = grid[bottom][j - 1];
                }
                
                for(int j=bottom; j > top; j--){
                    grid[j][left] = grid[j - 1][left];
                }
                
                grid[top + 1][left] = temp;
            }
            
            top++;
            left++;
            right--;
            bottom--;
        }
        
        return grid;
    }
}
