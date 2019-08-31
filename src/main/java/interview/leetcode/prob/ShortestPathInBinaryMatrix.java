package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

 

Example 1:

Input: [[0,1],[1,0]]


Output: 2

Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]


Output: 4

 

Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
Accepted
7,642
Submissions
20,986
 * @author jojo
 * Aug 31, 2019 1:24:45 PM
 */
public class ShortestPathInBinaryMatrix {
	public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1){
            return -1;
        }
        
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        grid[0][0] = 2;
        
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};
        int step = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            
            while(size-- > 0){
                int[] top = queue.poll();
                
                if(top[0] == m-1 && top[1] == n-1){
                    return step;
                }
                
                for(int[] move : moves){
                    int x = top[0] + move[0], y = top[1] + move[1];
                    
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 0){
                        continue;
                    }
                    
                    queue.offer(new int[]{x,y});
                    grid[x][y] = 2;
                }
            }
            
            step++;
        }
        
        return -1;
    }
}
