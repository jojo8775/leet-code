package interview.leetcode.prob;

/**
 * You are given a 0-indexed n x n grid where n is odd, and grid[r][c] is 0, 1, or 2.

We say that a cell belongs to the Letter Y if it belongs to one of the following:

The diagonal starting at the top-left cell and ending at the center cell of the grid.
The diagonal starting at the top-right cell and ending at the center cell of the grid.
The vertical line starting at the center cell and ending at the bottom border of the grid.
The Letter Y is written on the grid if and only if:

All values at cells belonging to the Y are equal.
All values at cells not belonging to the Y are equal.
The values at cells belonging to the Y are different from the values at cells not belonging to the Y.
Return the minimum number of operations needed to write the letter Y on the grid given that in one operation you can change the value at any cell to 0, 1, or 2.

 

Example 1:


Input: grid = [[1,2,2],[1,1,0],[0,1,0]]
Output: 3
Explanation: We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 1 while those that do not belong to Y are equal to 0.
It can be shown that 3 is the minimum number of operations needed to write Y on the grid.
Example 2:


Input: grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
Output: 12
Explanation: We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 0 while those that do not belong to Y are equal to 2. 
It can be shown that 12 is the minimum number of operations needed to write Y on the grid.
 

Constraints:

3 <= n <= 49
n == grid.length == grid[i].length
0 <= grid[i][j] <= 2
n is odd.
Accepted
20,165
Submissions
31,750
 * 
 * Nov 14, 2024 - 1:36:50 AM
 * Jojo 
 */
public class MinimumOperationsToWriteTheLetterYOnGrid {
	public int minimumOperationsToWriteY(int[][] grid) {
        int[] y_arr = findYChanges(grid);
        
        int[] o_y_arr = findOutsideY(grid);
        
        int y0 = y_arr[1] + y_arr[2] + o_y_arr[0] + Math.min(o_y_arr[1] , o_y_arr[2]);
        
        int y1 = y_arr[0] + y_arr[2] + o_y_arr[1] + Math.min(o_y_arr[0] , o_y_arr[2]);
        
        int y2 = y_arr[0] + y_arr[1] + o_y_arr[2] + Math.min(o_y_arr[0] , o_y_arr[1]);
        
        return Math.min(y0, Math.min(y1, y2));
    }
    
    public int[] findYChanges(int[][] grid){
        int i = 0, j = grid.length - 1;
        
        int[] arr = new int[3];
        
        while(i != j){
            arr[grid[i][i]]++;
            arr[grid[i][j]]++;
            
            grid[i][i] = grid[i][j] = -1;
            
            i++;
            j--;
        }
        
        while(i < grid.length){
            arr[grid[i][j]]++;
            grid[i][j] = -1;
            i++;
        }
        
        return arr;
    }
    
    public int[] findOutsideY(int[][] grid){
        int[] arr = new int[3]; 
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(grid[i][j] != -1){
                    arr[grid[i][j]]++;
                    grid[i][j] = -1;
                }
            }
        }
        
        return arr;
    }
}
