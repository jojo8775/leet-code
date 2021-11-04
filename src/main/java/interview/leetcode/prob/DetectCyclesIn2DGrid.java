package interview.leetcode.prob;

/**
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.
 * @author jojo
 * Nov 3, 2021 11:41:09 PM
 */
public class DetectCyclesIn2DGrid {
    private int[][] moves = {{0,1},{1,0},{0,-1},{-1,0}};
    
    public boolean containsCycle(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return false;
        }
        
        int m = grid.length, n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        boolean[][] failedSpot = new boolean[m][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                boolean status = findway(i, j, grid, visited, m, n, 0, failedSpot);
                if(status){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean findway(int i, int j, char[][] grid, boolean[][] visited, int m, int n, int dir, boolean[][] failedSpot){
        int oppositeDir = (dir + 2) % 4;
        
        if(failedSpot[i][j]){
            return false;
        }
        
        if(visited[i][j]){
            return true;
        }
        
        visited[i][j] = true;
                
        for(int k=0; k<4; k++){
            if(k == oppositeDir){
                continue;
            }
            
            int x = i+moves[k][0],  y = j+moves[k][1];
            
            if(x < 0 || x >= m || y < 0 || y >= n || grid[i][j] != grid[x][y]){
                continue;
            }
            
            boolean status = findway(x, y, grid, visited, m, n, k, failedSpot);
            if(status){
                return true;
            }
        }
        
        failedSpot[i][j] = true;
        visited[i][j] = false;
        return false;
    }
}
