package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

 

Example 1:



Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 
Example 2:



Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:



Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
 

Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9

 * @author jojo
 * Aug 31, 2019 3:51:26 AM
 */
public class PathWithMaximumMinimumValue {
	public int maximumMinimumPath(int[][] A) {
        int[][] moves = {{0,1},{1,0},{0,-1},{-1,0}};
        
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> b[2] - a[2]); // since max of min is needed
        queue.offer(new int[]{0,0,A[0][0]}); // [0] = i, [1] = j, [2] = min in the path;
        
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            
            if(top[0] == m-1 && top[1] == n-1){
                return top[2];
            }
            
            visited[top[0]][top[1]] = true;
            
            for(int[] move : moves){
                int x = top[0] + move[0], y = top[1] + move[1];
                
                if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y]){
                    continue;
                }
                
                queue.offer(new int[]{x, y, Math.min(top[2], A[x][y])});
            }
        }
        
        return -1;
    }
}
