package interview.leetcode.prob;

/**
 * 
You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).

Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

Example 1:


Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown.
Example 2:

Input: n = 2
Output: 54
Example 3:

Input: n = 3
Output: 246
Example 4:

Input: n = 7
Output: 106494
Example 5:

Input: n = 5000
Output: 30228214
 

Constraints:

n == grid.length
grid[i].length == 3
1 <= n <= 5000
Accepted
18,934
Submissions
30,784
 * @author jojo
 * Jul 21, 2021  9:39:39 PM
 */
public class NumberOfWaysToPainNx3Grid {
	int mod = (int) (1e9) + 7;

	public int numOfWays(int n) {
		int[][][][] dp = new int[n + 1][4][4][4];
		int[] colors = { 1, 2, 3 };

		return dfs(n, 0, 0, 0, dp, colors);
	}

	// c1, c2, c3 are the colors of the previous rows.
	private int dfs(int n, int c1, int c2, int c3, int[][][][] dp, int[] colors) {
		// if there is no more rows to paint.
		if (n == 0) {
			return 1;
		}

		// this is for memorization. After the first round, when we start with 2 and 3,
		// this will come handy.
		if (dp[n][c1][c2][c3] != 0) {
			return dp[n][c1][c2][c3];
		}

		int ans = 0;

		for (int a : colors) {
			// checking if prevous has same color
			if (c1 == a) {
				continue;
			}

			for (int b : colors) {
				// checking if prevous and left has same color
				if (b == c2 || a == b) {
					continue;
				}

				for (int c : colors) {
					// checking if previous and left has same color
					if (c == c3 || c == b) {
						continue;
					}

					ans += dfs(n - 1, a, b, c, dp, colors);

					ans %= mod;
				}
			}
		}

		dp[n][c1][c2][c3] = ans;
		return ans;
	}
}
