package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
 * @author jojo
 *
 */
public class TrappingRainWaterII {
	public int trapRainWater(int[][] heights) {
		if (heights.length == 0 || heights[0].length == 0) {
			return 0;
		}

		PriorityQueue<Cell> queue = new PriorityQueue<Cell>((Cell c1, Cell c2) -> c1.height - c2.height);

		int len = heights.length, width = heights[0].length;

		// adding all the edges first
		boolean[][] visited = new boolean[len][width];
		for (int i = 0; i < len; i++) {
			queue.add(new Cell(i, 0, heights[i][0]));
			queue.add(new Cell(i, width - 1, heights[i][width - 1]));
			visited[i][0] = true;
			visited[i][width - 1] = true;
		}

		for (int i = 0; i < width; i++) {
			queue.add(new Cell(0, i, heights[0][i]));
			queue.add(new Cell(len - 1, i, heights[len - 1][i]));
			visited[0][i] = true;
			visited[len - 1][i] = true;
		}

		int[][] moves = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		int maxTrappedWater = 0;

		while (!queue.isEmpty()) {
			Cell top = queue.poll();

			for (int[] move : moves) {
				int col = top.col + move[0];
				int row = top.row + move[1];

				if (col >= 0 && col < len && row >= 0 && row < width && !visited[col][row]) {
					visited[col][row] = true;
					maxTrappedWater += Math.max(0, top.height - heights[col][row]);
					queue.offer(new Cell(col, row, Math.max(heights[col][row], top.height)));
				}
			}
		}

		return maxTrappedWater;
	}

	private static class Cell {
		int col, row, height;

		public Cell(int col, int row, int height) {
			this.col = col;
			this.row = row;
			this.height = height;
		}
	}
}
