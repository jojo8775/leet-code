package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

 

Example 1:


Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:


Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
Each value grid[i][j] is unique.
 * @author jojo
 * Sep 29, 2022 6:54:34 PM
 */
public class SwimInRisingWater {
	/**
	 * The key in this problem is to understand that all is being asked is the highest wait time in the route. It doesnt matters the length because 
	 * according to the problem statement the movement from one square to another square takes 0 time.
	 * @param grid
	 * @return
	 */
    public int swimInWater(int[][] grid) {
        int m = grid.length,  n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{0,0, grid[0][0]});
        visited[0][0] =  true;

        int[][] moves = {{0,1},{1,0},{0,-1},{-1,0}};
        int result = 0;

        while(!pq.isEmpty()){
            int[] top = pq.poll();

            //System.out.println("x:" + top[0] + "  y:" + top[1] + "  val:" + top[2]);

            result = Math.max(result, top[2]);

            if(top[0] == m-1 && top[1] == n-1){
                break;
            }

            for(int[] move : moves){
                int x = move[0] + top[0], y = move[1] + top[1];

                if(x < 0 || x>= m || y < 0 || y >= n || visited[x][y]){
                    continue;
                }

                visited[x][y] = true;
                pq.offer(new int[]{x,y,grid[x][y]});
            }
        }

        return result;
    }
}
