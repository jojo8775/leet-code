package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.
 
 * @author jojo
 * Sep 3, 2019 12:39:59 AM
 */
public class MakingALargeIsland {
	public int largestIsland(int[][] grid) {
		int m = grid.length, n = grid[0].length;

		int[][] dp = new int[m][n];
		int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		int max = 0, islandMark = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1 && dp[i][j] == 0) {
					dfs(grid, dp, i, j, moves, --islandMark);
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					int val = calculate(grid, dp, moves, i, j);
					System.out.println("val: " + val);
					max = Math.max(max, val);
				}
			}
		}

		return max;
	}

	private int dfs(int[][] grid, int[][] dp, int i, int j, int[][] moves, int mark) {
		Stack<int[]> stack = new Stack<>();
		List<int[]> list = new ArrayList<>();

		stack.push(new int[] { i, j });
		grid[i][j] = mark;

		while (!stack.isEmpty()) {
			int[] top = stack.pop();

			list.add(top);

			for (int[] m : moves) {
				int x = top[0] + m[0], y = top[1] + m[1];

				if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
					continue;
				}

				grid[x][y] = mark;
				stack.push(new int[] { x, y });
			}
		}

		int size = list.size();
		for (int[] e : list) {
			dp[e[0]][e[1]] = size;
		}

		return size;
	}

	private int calculate(int[][] grid, int[][] dp, int[][] moves, int i, int j) {
		int size = 1;

		Set<Integer> marks = new HashSet<>();

		for (int[] m : moves) {
			int x = i + m[0], y = j + m[1];

			if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
				continue;
			}

			if (marks.add(grid[x][y])) {
				size += dp[x][y];
			}
		}

		return size;
	}
}
