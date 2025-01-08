package interview.leetcode.prob;

/**
 * You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

 

Example 1:


Input: grid = [[1,1],[3,4]]
Output: 8
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
- Paths with length 3: [1 -> 3 -> 4].
The total number of paths is 4 + 3 + 1 = 8.
Example 2:

Input: grid = [[1],[2]]
Output: 3
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [2].
- Paths with length 2: [1 -> 2].
The total number of paths is 2 + 1 = 3.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
1 <= grid[i][j] <= 105
Seen this question in a real interview before?
1/5
Yes
No
Accepted
72.3K
Submissions
124.9K
Acceptance Rate
57.9%
 * 
 * Jan 6, 2025 - 11:17:28 PM
 * Jojo 
 */
public class NumberOfIncreasingPathInAGrid {
	// same as https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

    private final int MOD = (int)(1e9 + 7);

    public int countPaths(int[][] grid) {
        int count = 0;
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};

        Integer[][] memo = new Integer[grid.length][grid[0].length];

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                int val = dfs(grid, moves, i, j, memo);

                count += val;
                count %= MOD;
            }
        }

        return count;
    }

    private int dfs(int[][] grid, int[][] moves, int i, int j, Integer[][] memo){
        if(memo[i][j] != null){
            return memo[i][j];
        }

        int pathCount = 1;
        
        for(int[] m : moves){
            int x = m[0] + i, y = m[1] + j;

            if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[i][j] >= grid[x][y]){
                continue;
            }

            pathCount += (dfs(grid, moves, x, y, memo) % MOD);
            pathCount %= MOD;
        }

        //System.out.println("---   i: " + i + "   j: " + j + "  pathCount: " + pathCount);
        return memo[i][j] = pathCount;
    }
}
