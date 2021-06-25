package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 

Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
Accepted
8,003
Submissions
13,355
 * @author jojo
 * Jun 24, 2021  11:26:03 PM
 */
public class CountSubIsland {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        
        for(int i=0; i<grid2.length; i++){
            for(int j=0; j<grid2[i].length; j++){
                if(grid2[i][j] == 1 && grid1[i][j] == 1){
                    if(isSubIsland(grid1, grid2, i, j)){
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    private boolean isSubIsland(int[][] grid1, int[][] grid2, int i, int j){
        int[][] moves = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        grid2[i][j] = -1;
        
        boolean status = true;
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            
            for(int[] m : moves){
                int x = top[0] + m[0], y = top[1] + m[1];
                
                if(x < 0 || x >= grid2.length || y < 0 || y >= grid2[0].length || grid2[x][y] != 1){
                    continue;
                }
                
                if(grid1[x][y] != 1){
                    status = false;
                }
                
                grid2[x][y] = -1;
                queue.offer(new int[]{x,y});
            }
        }
        
        return status;
    }
}
