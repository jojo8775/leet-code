package interview.leetcode.prob;

/**
 * You are given an m x n grid. A robot starts at the top-left corner of the grid (0, 0) and wants to reach the bottom-right corner (m - 1, n - 1). The robot can move either right or down at any point in time.

The grid contains a value coins[i][j] in each cell:

If coins[i][j] >= 0, the robot gains that many coins.
If coins[i][j] < 0, the robot encounters a robber, and the robber steals the absolute value of coins[i][j] coins.
The robot has a special ability to neutralize robbers in at most 2 cells on its path, preventing them from stealing coins in those cells.

Note: The robot's total coins can be negative.

Return the maximum profit the robot can gain on the route.

 

Example 1:

Input: coins = [[0,1,-1],[1,-2,3],[2,-3,4]]

Output: 8

Explanation:

An optimal path for maximum coins is:

Start at (0, 0) with 0 coins (total coins = 0).
Move to (0, 1), gaining 1 coin (total coins = 0 + 1 = 1).
Move to (1, 1), where there's a robber stealing 2 coins. The robot uses one neutralization here, avoiding the robbery (total coins = 1).
Move to (1, 2), gaining 3 coins (total coins = 1 + 3 = 4).
Move to (2, 2), gaining 4 coins (total coins = 4 + 4 = 8).
Example 2:

Input: coins = [[10,10,10],[10,10,10]]

Output: 40

Explanation:

An optimal path for maximum coins is:

Start at (0, 0) with 10 coins (total coins = 10).
Move to (0, 1), gaining 10 coins (total coins = 10 + 10 = 20).
Move to (0, 2), gaining another 10 coins (total coins = 20 + 10 = 30).
Move to (1, 2), gaining the final 10 coins (total coins = 30 + 10 = 40).
 

Constraints:

m == coins.length
n == coins[i].length
1 <= m, n <= 500
-1000 <= coins[i][j] <= 1000
Seen this question in a real interview before?
1/5
Yes
No
Accepted
8.3K
Submissions
35.3K
Acceptance Rate
23.6%
 * @author jojo
 * Jan. 11, 2025 11:00:13 p.m.
 */
public class MaximumAmountOfMoneyRobotsCanEarn {
	int[][] moves = { { 0, 1 }, { 1, 0 } };

	public int maximumAmount(int[][] coins) {
		Integer[][][] memo = new Integer[coins.length][coins[0].length][3];
		int val1 = coins[0][0] + dp(coins, 0, 0, 2, memo);
		if (coins[0][0] < 0) {
			val1 = Math.max(val1, dp(coins, 0, 0, 1, memo));
		}

		return val1;
	}

	private int dp(int[][] coins, int i, int j, int k, Integer[][][] memo) {
		if (memo[i][j][k] != null) {
			return memo[i][j][k];
		}

		Integer max = null;

		for (int[] move : moves) {
			int x = i + move[0], y = j + move[1];

			if (x < 0 || x >= coins.length || y < 0 || y >= coins[0].length) {
				continue;
			}

			int val1 = coins[x][y] + dp(coins, x, y, k, memo);
			if (coins[x][y] < 0 && k > 0) {
				val1 = Math.max(val1, dp(coins, x, y, k - 1, memo));
			}

			// System.out.println(val1);

			if (max == null) {
				max = val1;
			} else {
				max = Math.max(max, val1);
			}
		}

		// return max;
		return memo[i][j][k] = max == null ? 0 : max;
	}
}
