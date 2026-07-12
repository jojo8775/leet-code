package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).

We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).

Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares. We are also not allowed to walk outside of the grid.

Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.

 

Example 1:

Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
Output: false
Explanation: The target square is inaccessible starting from the source square because we cannot move.
We cannot move north or east because those squares are blocked.
We cannot move south or west because we cannot go outside of the grid.
Example 2:

Input: blocked = [], source = [0,0], target = [999999,999999]
Output: true
Explanation: Because there are no blocked cells, it is possible to reach the target square.
 

Constraints:

0 <= blocked.length <= 200
blocked[i].length == 2
0 <= xi, yi < 106
source.length == target.length == 2
0 <= sx, sy, tx, ty < 106
source != target
It is guaranteed that source and target are not blocked.
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
29,444/80K
Acceptance Rate
36.8%
 * 
 * chiranjeebnandy
 * Jul 11, 2026  2026  2:48:29 PM
 */
public class EscapeALargeMaze {
	public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blockedGrids = new HashSet<>();

        for(int[] b : blocked){
            blockedGrids.add(b[0] + "-" + b[1]);
        }

        if(blockedGrids.size() == 0){
            return true;
        }

        int sourceToTarget = bfs(blockedGrids, source, target);

        // this means the path was successfully blocked.
        if(sourceToTarget == 0){
            return false;
        }

        // this means target was found.
        if(sourceToTarget == 2){
            return true;
        }

        // checking if there is a path from target to source
        // this is because we are terminating the grid incase the source 
        // and target are far apart.
        return bfs(blockedGrids, target, source) != 0;
    }

    // 0 -> the path was blocked 
    // 1 -> the path escaped the max possible blocked area. So potentially there is a path 
    // 2 -> the path has reached from souce to dest.
    private int bfs(Set<String> blocked, int[] source, int[] dest){
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();

        visited.add(source[0] + "-" + source[1]);
        queue.offer(source);

        int visitedCount = 1; // since source is visited already
        int limit = 1000000;

        int blockCellCount = blocked.size();

        // (n * (n-1))/2 where n is max blocked cells. This formula is to find the max diagonal
        // from the edges using the number of blocked cells.
        int maxFreeCellsBlocked = (blockCellCount * (blockCellCount - 1)) / 2; 
        
        if(maxFreeCellsBlocked < 1){
            maxFreeCellsBlocked = 1;
        }

        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};

        while(!queue.isEmpty()){
            int[] top = queue.poll();
            
            if(top[0] == dest[0] && top[1] == dest[1]){
                return 2;
            }
            
            for(int[] m : moves){
                int x = top[0] + m[0];
                int y = top[1] + m[1];

                String key = x + "-" + y;

                if(blocked.contains(key) || visited.contains(key) || x < 0 || x >= limit || y < 0 || y >= limit){
                    continue;
                }

                visited.add(key);
                queue.offer(new int[]{x,y});
                visitedCount++;

                if(visitedCount > maxFreeCellsBlocked){
                    // the path escaped the max free blocks which can be blocked. Which means
                    // there will be definetly a path
                    return 1; 
                }
            }
        }

        return 0;
    }
}
