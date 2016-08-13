package interview.leetcode.prob;

/**
 * Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * @author jojo
 *
 */
public class LongestIncreasingPath {
	// this uses memorization and dynamic problem
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int longestPath = 0;

		int[][] mem = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				longestPath = Math.max(longestPath, findPath(matrix, i, j, mem));
			}
		}

		return longestPath;
	}

	int[] posX = { 0, 0, 1, -1 };
	int[] posY = { 1, -1, 0, 0 };

	private int findPath(int[][] matrix, int i, int j, int[][] mem) {
		if (mem[i][j] != 0) {
			return mem[i][j];
		}

		int max = 1;

		for (int a = 0; a < 4; a++) {
			int x = j + posX[a];
			int y = i + posY[a];

			if (x >= 0 && y >= 0 && x < mem[0].length && y < mem.length && matrix[i][j] > matrix[y][x]) {
				int len = 1 + findPath(matrix, y, x, mem);
				max = Math.max(len, max);
			}
		}

		mem[i][j] = max;
		return max;
	}

	public static void main(String[] args) {
		int[][] grid = new int[3][3];
		grid[0] = new int[] { 9, 9, 4 };
		grid[1] = new int[] { 6, 6, 8 };
		grid[2] = new int[] { 2, 1, 1 };

		System.out.println(new LongestIncreasingPath().longestIncreasingPath(grid));
	}
}
