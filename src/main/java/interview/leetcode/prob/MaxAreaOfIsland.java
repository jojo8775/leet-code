package interview.leetcode.prob;

import java.util.Stack;

/**
 * 
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.

Accepted
166,662
Submissions
264,663
 * @author jojo
 * Sep 2, 2020  11:29:08 PM
 */
public class MaxAreaOfIsland {
	public int maxAreaOfIsland(int[][] grid) {
        int[] result = {0};
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    findMax(grid, i, j, result);
                }
            }
        }
        
        return result[0];
    }
    
    private void findMax(int[][] grid, int i, int j, int[] result){
        int[][] moves = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i,j});
        grid[i][j] = 2;
        
        int count = 0;
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            count++;
            //System.out.println("x: " + top[0] + "  y: " + top[1]);
            
            for(int[] move : moves){
                int x = top[0] + move[0], y = top[1] + move[1];
                
                if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1){
                    continue;
                }
                
                grid[x][y] = 2;
                stack.push(new int[]{x,y});
            }
        }
        
        result[0] = Math.max(result[0], count);
    }
}
