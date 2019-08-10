package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

 

Example 1:



Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.

 * @author jojo
 * Jul 29, 2019 9:17:46 PM
 */
public class RottingOranges {
	public int orangesRotting(int[][] grid) {
		int m = grid.length, n = grid[0].length, freshCount = 0,  minutes = 0;

		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 2) {
					queue.offer(new int[] { i, j });
				} else if (grid[i][j] == 1) {
					freshCount++;
				}
			}
		}

		if (freshCount == 0) {
			return 0;
		}

		int[][] moves = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int[] top = queue.poll();

				for (int j = 0; j < 4; j++) {
					int x = top[0] + moves[j][0];
					int y = top[1] + moves[j][1];

					if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || grid[x][y] == 2) {
						continue;
					}

					grid[x][y] = 2;
					freshCount--;
					queue.offer(new int[] { x, y });
				}
			}

			if (!queue.isEmpty()) {
				minutes++;
			}
		}

		return freshCount == 0 ? minutes : -1;
	}
}
