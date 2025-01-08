package interview.leetcode.prob;
//same as https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/?envType=company&envId=adobe&favoriteSlug=adobe-six-months
public class LongestIncreasingPathInAMatrix {
	int[] dx = { -1, 1, 0, 0 };
	int[] dy = { 0, 0, -1, 1 };

	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		int[][] mem = new int[matrix.length][matrix[0].length];
		int longest = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				longest = Math.max(longest, dfs(matrix, i, j, mem));
			}
		}

		return longest;
	}

	public int dfs(int[][] matrix, int i, int j, int[][] mem) {
		if (mem[i][j] != 0)
			return mem[i][j];

		for (int m = 0; m < 4; m++) {
			int x = i + dx[m];
			int y = j + dy[m];

			if (x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
				mem[i][j] = Math.max(mem[i][j], dfs(matrix, x, y, mem));
			}
		}

		return ++mem[i][j];
	}

	public static void main(String[] args) {
		int[][] grid = new int[2][3];
		grid[0] = createArr(1, 2, 3);
		grid[1] = createArr(1, 1, 4);

		System.out.println(new LongestIncreasingPathInAMatrix().longestIncreasingPath(grid));
	}

	private static int[] createArr(int... a) {
		return a;
	}
}

