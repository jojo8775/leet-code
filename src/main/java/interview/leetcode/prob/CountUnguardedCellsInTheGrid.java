package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.

A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.

Return the number of unoccupied cells that are not guarded.

 

Example 1:


Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.
Example 2:


Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.
 

Constraints:

1 <= m, n <= 105
2 <= m * n <= 105
1 <= guards.length, walls.length <= 5 * 104
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.
Accepted
38,941
Submissions
64,341
 * 
 * Nov 20, 2024 - 11:15:25 PM
 * Jojo 
 */
public class CountUnguardedCellsInTheGrid {
	public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};
        
        int emptyCellCount = m * n;
        
        Queue<int[]> queue = new LinkedList<>();
        
        int[][] visited = new int[m][n];
        
        for(int[] g : guards){
            visited[g[0]][g[1]] = -1;
            emptyCellCount--;
            
            queue.offer(new int[]{g[0], g[1], 0});
            queue.offer(new int[]{g[0], g[1], 1});
            queue.offer(new int[]{g[0], g[1], 2});
            queue.offer(new int[]{g[0], g[1], 3});
        }
        
        for(int[] w : walls){
            visited[w[0]][w[1]] = -2;
            emptyCellCount--;
        }
        
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            int x = top[0] + moves[top[2]][0], y = top[1] + moves[top[2]][1];
            
            while(x >= 0 && x < m && y >= 0 && y < n && visited[x][y] >= 0){
                if(visited[x][y] == 0){
                    visited[x][y] = 1;
                    emptyCellCount--;   
                }
                
                x += moves[top[2]][0];
                y += moves[top[2]][1];
            }
        }
        
        return emptyCellCount;
    }
}
