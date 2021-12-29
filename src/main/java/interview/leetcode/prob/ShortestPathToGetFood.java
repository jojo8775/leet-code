package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

 

Example 1:


Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.
Example 2:


Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.
Example 3:


Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.
Accepted
18,330
Submissions
33,838
 * @author jojo
 * Dec 28, 2021 10:35:43 PM
 */
public class ShortestPathToGetFood {
	public int getFood(char[][] grid) {
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};        
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i=0; i<grid.length && queue.isEmpty(); i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '*'){
                    grid[i][j] = 'X';
                    queue.offer(new int[]{i,j});
                    break;
                }
            }
        }
        
        int dist = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            dist++;
            while(size-- > 0){
                int[] top = queue.poll();
                for(int[] m : moves){
                    int x = top[0] + m[0], y = top[1] + m[1];
                    if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 'X'){
                        continue;
                    }
                    
                    if(grid[x][y] == '#'){
                        return dist;
                    }
                    else if(grid[x][y] == 'O'){
                        queue.offer(new int[] {x,y});
                        grid[x][y] = 'X';
                    }
                }
            }
        }
        
        return -1;
    }
}
