package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.


You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.

 

Example 1:


Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
Example 2:


Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
Example 3:

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
Example 4:

Input: grid = [[1,1,1,1,1,1,3]]
Output: true
Example 5:

Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
Output: true
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6
Accepted
18,711
Submissions
40,381
 * @author jojo
 * Nov 8, 2021 10:40:31 PM
 */
public class CheckIfThereIsAValidPathInAGrid {
	public boolean hasValidPath(int[][] grid) {
		int[][][] dirs = new int[7][][];
		dirs[1] = new int[][] {{0,1}, {0,-1}};
		dirs[2] = new int[][] {{-1,0}, {1,0}};
		dirs[3] = new int[][] {{0,-1}, {1,0}};
		dirs[4] = new int[][] {{1,0}, {0,1}};
		dirs[5] = new int[][] {{0,-1}, {-1,0}};
		dirs[6] = new int[][] {{-1,0}, {0,1}};
		
        
        int m = grid.length, n = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            int x = top[0], y = top[1];
            
            for(int[] dir : dirs[grid[x][y]]){
                int nx = x + dir[0], ny = y + dir[1];
                
                if(nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]){
                    continue;
                }
                
                // idea is to go to the next chamber and check if I can come back. 
                for(int[] nDir : dirs[grid[nx][ny]]){
                    if(nx + nDir[0] == x && ny + nDir[1] == y){
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        
        return visited[m-1][n-1];
    }
}
