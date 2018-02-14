package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
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
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 * @author jojo
 *Feb 4, 20182:32:39 AM
 */
public class NumberOfIslandsII {
    int[][] moves = {{0,-1}, {0,1}, {1,0}, {-1,0}};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<Integer>();
        // if the input is not valid then return blank
        if (m<=0 || n<=0){
            return result;
        }
        
        // used as a tree to store parent child hierarchy of islands
        int[] islands = new int[m*n];
        // fill all islands with water
        Arrays.fill(islands, -1);
        
        int islandCount = 0;
        
        for(int[] pos : positions){
            int currentIsland = n*pos[0] + pos[1];
            
            // making parent information as self
            islands[currentIsland] = currentIsland;
            
            // assuming the current island is a satellite island
            islandCount ++;
            
            // checking for neigour islands
            for(int[] move : moves){
                int x = pos[1] + move[1];
                int y = pos[0] + move[0];
                int neighborIsland = n*y + x;
                
                // is the neighbor position is water or outside range then skip
                if(x < 0 || x >= n || y < 0 || y>=m || islands[neighborIsland] == -1){
                    continue;
                }
                
                // find the parent island of the neighbor island
                int parentIsland = findParentIsland(islands, neighborIsland);
                
                // if the neighbor island contains a different parent island then its time to join them
                if(parentIsland != currentIsland){
                    // updating the parent of current island
                    islands[currentIsland] = parentIsland;
                    // moving the curent island to the neighbour parent island to connect the chain
                    currentIsland = parentIsland;
                    // decreasing the count as current island belongs a bigger island 
                    islandCount--;
                }
            }
            
            result.add(islandCount);
        }
        
        return result;
    }
    
    private int findParentIsland(int[] islands, int parentIsland){
        // if parent is not same as self then it means it is a part of a bigger island
        while(parentIsland != islands[parentIsland]){
            parentIsland = islands[parentIsland];
        }
        
        return parentIsland;
    }
}
