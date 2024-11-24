package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.

 

Example 1:

Input: moveTime = [[0,4],[4,4]]

Output: 7

Explanation:

The minimum time required is 7 seconds.

At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.
Example 2:

Input: moveTime = [[0,0,0,0],[0,0,0,0]]

Output: 6

Explanation:

The minimum time required is 6 seconds.

At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
At time t == 3, move from room (1, 1) to room (1, 2) in one second.
At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.
Example 3:

Input: moveTime = [[0,1],[1,2]]

Output: 4

 

Constraints:

2 <= n == moveTime.length <= 750
2 <= m == moveTime[i].length <= 750
0 <= moveTime[i][j] <= 109
Accepted
7,495
Submissions
17,141
 * 
 * Nov 3, 2024 - 3:23:21 PM
 * Jojo 
 */
public class FindMinimumTimeToReachLastRoomII {
	public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        
        Integer[][] minTime = new Integer[m][n];
        
        // 0: min time sofar
        // 1: i co-ordinate 
        // 2: j co-ordinate 
        // 3: 1 - represnets odd position, 0 - represents even position 
        pq.offer(new int[]{0,0,0,0});
        
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            
            if(top[1] == m-1 && top[2] == n-1){
                return top[0];
            }
            
            if(minTime[top[1]][top[2]] != null && minTime[top[1]][top[2]] < top[0]){
                continue;
            }
            
            for(int[] move : moves){
                int x = move[0] + top[1], y = move[1] + top[2];
                
                if(x < 0 || x >= m || y < 0 || y >= n){
                    continue;
                }
                
                // if cur is odd position then cost is 2 otherwise the cost is 1
                int nextMoveCost = Math.max(moveTime[x][y], top[0]) + (top[3] == 1 ? 2 : 1);
                
                if(minTime[x][y] == null || minTime[x][y] > nextMoveCost){
                    minTime[x][y] = nextMoveCost;
                    
                    // if the current position is even then next position will be odd and vice versa
                    pq.offer(new int[]{nextMoveCost, x, y, (top[3] == 1 ? 0 : 1)});
                }
            }
        }
        
        return -1;
    }
}
