package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:


Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:


Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
Accepted
67,366
Submissions
153,709
 * @author jojo
 * Dec 28, 2021 11:14:45 PM
 */
public class ShortestPathInAGridWithObstacleElemination {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        int[][] moves = {{0,1},{0,-1},{-1,0},{1,0}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,k});

        // we need to track K terms of visited because we are terminating the BFS. 
        boolean[][][] visited = new boolean[m][n][k+1];
        visited[0][0][k] = true;
        
        int dist = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            //System.out.println("dist: " + dist);
            
            while(size -- > 0){
                int[] top = queue.poll();
                
                if(top[0] == m - 1 && top[1] == n - 1){
                    return dist;
                }
                
                for(int[] move : moves){
                    int x = top[0] + move[0], y = top[1] + move[1];
                    
                    if(x < 0 || x >= m || y < 0 || y >= n){
                        continue;
                    }
                    
                    int nextK = grid[x][y] == 1 ? top[2] - 1 : top[2];
                    
                    if(nextK >=0 && !visited[x][y][nextK]){
                        visited[x][y][nextK] = true;
                        queue.offer(new int[]{x,y,nextK});
                    }
                }
            }
            
            dist++;
        }
        
        return -1;
    }
}
