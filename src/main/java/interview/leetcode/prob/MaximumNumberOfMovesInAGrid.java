package interview.leetcode.prob;

/**
 * You are given a 0-indexed m x n matrix grid consisting of positive integers.

You can start at any cell in the first column of the matrix, and traverse the grid in the following way:

From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

 

Example 1:


Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.
Example 2:


Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
1 <= grid[i][j] <= 106
Accepted
44,252
Submissions
84,166
 * 
 * 
 * Oct 28, 2024 - 10:57:56 PM
 * Jojo 
 */
public class MaximumNumberOfMovesInAGrid {
	public int maxMoves(int[][] grid) {
        //return topdown(grid);
        return bottomup(grid);
    }
    
    private int bottomup(int[][] grid){
        
        // think about this move as as 
        //            x,y
        //          /
        //        /
        // i, j   --- x,y 
        ///       \
        //          \
        //           x,y
        int[][] moves = {{-1, 1}, {0, 1}, {1, 1}};
        
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        
        int max = 0;
        for(int j=n-2; j>=0; j--){
            for(int i=0; i<m; i++){
                for(int[] move : moves){
                    int x = i + move[0], y = j + move[1];
                    
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] <= grid[i][j]){
                        continue;
                    }
                    
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[x][y]);
                }
                
                if(j == 0){
                    max = Math.max(max, dp[i][0]);
                }
            }
        }
        
        return max;
    }
    
    private int topdown(int[][] grid){
        int max = 0;
        
        int m = grid.length, n = grid[0].length;
        
        Integer[][] memo = new Integer[m][n];
        
        for(int i=0; i<grid.length; i++){
            max = Math.max(max, dp(i, 0, grid, memo));
        }
        
        return max;
    }
    
    // visited is not needed becuase the path can only be from lesser to greater 
    private int dp(int i, int j, int[][] grid, Integer[][] memo){
        if(memo[i][j] != null){
            return memo[i][j];
        }
        
        int[][] moves = {{-1, 1}, {0, 1}, {1, 1}};
        
        int max = 0;
        
        for(int[] move : moves){
            int x = i + move[0], y = j + move[1];
            
            if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] <= grid[i][j]){
                continue;
            }
            
            max = Math.max(max, 1 + dp(x, y, grid, memo));
        }
        
        return memo[i][j] = max;
    }
}
