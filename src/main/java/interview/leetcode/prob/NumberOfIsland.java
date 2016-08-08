package interview.leetcode.prob;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 * @author jojo
 *
 */
public class NumberOfIsland {
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// land foung
				if (grid[i][j] == '1') {
					count++;
					// explore the area of the land
					explore(grid, i, j);
				}
			}
		}

		return count;
	}

	private void explore(char[][] grid, int i, int j) {
		if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0) {
			return;
		}

		if (grid[i][j] != '1') {
			return;
		}

		// mark it visited
		grid[i][j] = '#';

		explore(grid, i + 1, j); // down
		explore(grid, i - 1, j); // up
		explore(grid, i, j - 1); // left
		explore(grid, i, j + 1); // right
	}
}
