package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:
Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
Example 2:
Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
Example 3:
Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
Hint: size of the given matrix will not exceed 50x50.


 * @author jojo
 *Feb 10, 201812:30:04 PM
 */
public class CutOffTressForGolfEvent {
    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((int[] a, int[] b) -> {
            return a[0] - b[0];    
        });
        
        int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int m = forest.size(), n = forest.get(0).size();
        
        for(int i=0; i<m; i++){
            List<Integer> entry = forest.get(i);
            for(int j=0; j<n; j++){
                if(entry.get(j) > 1){
                    pQueue.offer(new int[]{entry.get(j), i, j});
                }
            }
        }
        
        int[] curPos = {0,0};
        
        int totalDist = 0, curDist = 0;
        
        while(!pQueue.isEmpty()){
            int[] destPos = pQueue.poll();
            curDist = bfs(forest, curPos, destPos, moves, m, n);
            curPos[0] = destPos[1];
            curPos[1] = destPos[2];
            
            if(curDist == -1){
                totalDist = -1;
                break;
            }
            else{
                totalDist += curDist;
            }
        }
        
        return totalDist;
    }
    
    private int bfs(List<List<Integer>> forest, int[] curPos, int[] dest, int[][] moves, int m, int n){
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue =  new LinkedList<>();
        queue.offer(new int[]{curPos[0], curPos[1]});
        
        int steps = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] top = queue.poll();
                if(top[0] == dest[1] && top[1] == dest[2]){
                    // forest.get(top[0]).set(top[1], 1);
                    return steps;
                }
                for(int[] move : moves){
                    int x = top[0] + move[0];
                    int y = top[1] + move[1];

                    if(x < 0 || x>= m || y<0 || y>= n || forest.get(x).get(y) == 0 || visited[x][y]){
                        continue;
                    }

                    visited[x][y] = true;
                    queue.offer(new int[] {x,y});
                }
            }
            
            steps++;
        }
        
        return -1;
    }
}
