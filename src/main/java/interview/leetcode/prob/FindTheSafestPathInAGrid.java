package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

A cell containing a thief if grid[r][c] = 1
An empty cell if grid[r][c] = 0
You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.

The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.

Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

 

Example 1:


Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
Output: 0
Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
Example 2:


Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
Example 3:


Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
Output: 2

Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
 

Constraints:

1 <= grid.length == n <= 400
grid[i].length == n
grid[i][j] is either 0 or 1.
There is at least one thief in the grid.
Accepted
25,659
Submissions
72,232
 * 
 * May 14, 2024 - 9:29:35 PM
 * Jojo 
 */
public class FindTheSafestPathInAGrid {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        
        if(grid.get(0).get(0) == 1 || grid.get(m-1).get(n-1) == 1){
            return 0;
        }
        
        int[][] arr = new int[m][n];
        
        Queue<int[]> queue = new LinkedList<>();
        
        // identifying the theif locations 
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid.get(i).get(j) == 1){
                    queue.offer(new int[]{i,j});
                }
                else{
                    arr[i][j] = -1;
                }
            }
        }
        
        int[][] moves = {{0,1}, {0, -1}, {1,0}, {-1,0}};
        
        int hop = 1;
        
        // performing a BFS to find the min dis from each theif 
        while(!queue.isEmpty()){
            int size = queue.size();
            
            while(size-- > 0){
                int[] top = queue.poll();
                
                for(int[] move : moves){
                    int x = top[0] + move[0], y = top[1] + move[1];
                    
                    if(x < 0 || x >= m || y < 0 || y >= n || arr[x][y] != -1){
                        continue;
                    }
                    
                    arr[x][y] = hop;
                    queue.offer(new int[]{x,y});
                }
            }
            
            hop++;
        }
        
        
        // using a max heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[2] - a[2]);
        pq.offer(new int[]{0,0, arr[0][0]});
        arr[0][0] = -1;
        
        // performing a traversal from 0,0 to m-1,n-1 and considering the min safeness so far.
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            
            if(top[0] == m-1 && top[1] == n - 1){
                return top[2];
            }
            
            int curMin = top[2];
            for(int[] move : moves){
                int x = top[0] + move[0], y = top[1] + move[1];
                    
                if(x < 0 || x >= m || y < 0 || y >= n || arr[x][y] == -1){
                    continue;
                }
                
                pq.offer(new int[]{x,y, Math.min(arr[x][y], curMin)});
                arr[x][y] = -1;
            }
        }
        
        return 0;
    }
}
