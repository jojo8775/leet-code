package interview.leetcode.prob;

import java.
/**
 * You are given an m x n grid where each cell contains one of the values 0, 1, or 2. You are also given an integer k.

You start from the top-left corner (0, 0) and want to reach the bottom-right corner (m - 1, n - 1) by moving only right or down.

Each cell contributes a specific score and incurs an associated cost, according to their cell values:

0: adds 0 to your score and costs 0.
1: adds 1 to your score and costs 1.
2: adds 2 to your score and costs 1. ​​​​​​​
Return the maximum score achievable without exceeding a total cost of k, or -1 if no valid path exists.

Note: If you reach the last cell but the total cost exceeds k, the path is invalid.

 

Example 1:

Input: grid = [[0, 1],[2, 0]], k = 1

Output: 2

Explanation:​​​​​​​

The optimal path is:

Cell	grid[i][j]	Score	Total
Score	Cost	Total
Cost
(0, 0)	0	0	0	0	0
(1, 0)	2	2	2	1	1
(1, 1)	0	0	2	0	1
Thus, the maximum possible score is 2.

Example 2:

Input: grid = [[0, 1],[1, 2]], k = 1

Output: -1

Explanation:

There is no path that reaches cell (1, 1)​​​​​​​ without exceeding cost k. Thus, the answer is -1.

 

Constraints:

1 <= m, n <= 200
0 <= k <= 103​​​​​​​
​​​​​​​grid[0][0] == 0
0 <= grid[i][j] <= 2
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
80,104/147.9K
Acceptance Rate
54.2%
 * @param grid
 * @param k
 * @return
 */
public class MaximumPathScoreInAGrid {
	public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int[][][] dp = new int[m][n][k+1];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][0] = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                for(int c=0; c<=k; c++){
                    if(dp[i][j][c] == -1){
                        continue;
                    }

                    if(i + 1 < m){
                        int val = grid[i+1][j];
                        int cost = val == 0 ? 0 : 1;

                        if(c + cost <= k){
                            dp[i+1][j][c+cost] = Math.max(dp[i+1][j][c+cost], dp[i][j][c] + val);
                        }
                    }
                    
                    if(j + 1 < n){
                        int val = grid[i][j+1];
                        int cost = val == 0 ? 0 : 1;

                        if(c + cost <= k){
                            dp[i][j+1][c+cost] = Math.max(dp[i][j+1][c+cost], dp[i][j][c] + val);
                        }
                    }
                }
            }
        }

        int result = -1;

        for(int i=0; i<=k; i++){
            result = Math.max(result, dp[m-1][n-1][i]);
        }

        return result;
    }
}
