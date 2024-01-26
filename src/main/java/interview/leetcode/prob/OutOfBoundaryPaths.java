package interview.leetcode.prob;

/**
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:


Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6
Example 2:


Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
 

Constraints:

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
Accepted
130,867
Submissions
291,932
 * @author jojo
 * Jan. 25, 2024 11:25:13 p.m.
 */
public class OutOfBoundaryPaths {
    long mod = (long)(1e9 + 7);
    
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Long[][][] memo = new Long[maxMove + 1][m][n];
        
        return (int)(findWays(m, n, maxMove, startRow, startColumn, memo) % mod);
    }
    
    private long findWays(int m, int n, int maxMoves, int i, int j, Long[][][] memo){
        if(i < 0 || j < 0 || i == m || j == n){
            return 1;
        }
        
        if(maxMoves <= 0){
            return 0;
        }
        
        if(memo[maxMoves][i][j] != null){
            return memo[maxMoves][i][j];
        }
        
        long val = findWays(m, n, maxMoves - 1, i-1, j, memo) % mod;
        val += (findWays(m, n, maxMoves - 1, i+1, j, memo) % mod);
        val += (findWays(m, n, maxMoves - 1, i, j-1, memo) % mod);
        val += (findWays(m, n, maxMoves - 1, i, j+1, memo) % mod);
        
        memo[maxMoves][i][j] = val;
        
        return val;
    }	
}
