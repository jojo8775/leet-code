package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?

Accepted
85,851
Submissions
216,093
 * @author jojo
 * Feb 10, 2021  12:00:28 AM
 */
public class NumberOfIslandII {
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parentArr = new int[m * n];
        Arrays.fill(parentArr, -1);
        
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        List<Integer> result = new ArrayList<>();
        int islandCount = 0;
        
        for(int[] p : positions){
            int idx = n * p[0] + p[1];
            
            // need to avoid duplicate islands.
            if(parentArr[idx] != -1){
                result.add(islandCount);
                continue;
            }
            
            parentArr[idx] = idx;
            
            // initially considering every addition as an island.
            islandCount++;
                        
            for(int[] d : dir){
                int x = d[0] + p[0], y = d[1] + p[1];
                
                int nIdx = x * n + y;
                
                // if the next spot is not water then time to find parent and union
                if(x < 0 || x >= m || y < 0 || y >= n || parentArr[nIdx] == -1){
                    continue;
                }
                
                // finding the parent of the connected roots.
                int root = find(parentArr, nIdx);
                
                // if the root of the connected island is not the same as the current one, then time to union then
                if(root != idx){
                    islandCount--;
                    parentArr[root] = idx; // this is improtant, need to connect the parent to point to the current.
                }
            }
            
            result.add(islandCount);
        }
        
        return result;
    }
    
    private int find(int[] arr, int idx){
        while(idx != arr[idx]){
            arr[idx] = arr[arr[idx]];
            idx = arr[idx];
        }
        
        return idx;
    }
}
