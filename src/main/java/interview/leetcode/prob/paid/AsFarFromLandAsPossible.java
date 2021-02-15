package interview.leetcode.prob.paid;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
Accepted
30,461
Submissions
67,349
 * @author jojo
 * Feb 14, 2021  11:32:27 PM
 */
public class AsFarFromLandAsPossible {
	public int maxDistance(int[][] grid) {

		int m = grid.length, n = grid[0].length;

		boolean[][] visited = new boolean[m][n];
		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					queue.offer(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

		int level = -1;

		int[][] moves = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] top = queue.poll();

				for (int[] move : moves) {
					int x = top[0] + move[0], y = top[1] + move[1];

					if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != 0) {
						continue;
					}

					visited[x][y] = true;
					queue.offer(new int[] { x, y });
				}
			}

			level++;
		}

		return level <= 0 ? -1 : level;
	}
}
