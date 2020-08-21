package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
Accepted
21,228
Submissions
35,111
 * @author jojo
 * Aug 20, 2020  11:44:42 PM
 */
public class NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        int count = 0;
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 0 && isClosedIsland(grid, i, j)){
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private boolean isClosedIsland(int[][] grid, int i, int j){
        int[][] moves = {{0,1},{1,0},{-1,0},{0,-1}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        
        boolean flag = true;
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            grid[top[0]][top[1]] = 2;
            
            for(int[] move : moves){
                int x = top[0] + move[0], y = top[1] + move[1];
                
                if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length){
                    flag = false;
                }
                else if(grid[x][y] == 0){
                    queue.offer(new int[]{x,y});
                }
            }
        }
        
        return flag;
    }
}
