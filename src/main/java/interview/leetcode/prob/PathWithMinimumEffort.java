package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

 

Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 

Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
Accepted
64,168
Submissions
124,959
 * @author jojo
 * Dec 29, 2021 11:05:43 PM
 */
public class PathWithMinimumEffort {
	int moves[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public int minimumEffortPath(int[][] heights) {
		int m = heights.length, n = heights[0].length;
		
		int[][] differenceMatrix = new int[m][n];
		
		for (int[] eachRow : differenceMatrix) {
			Arrays.fill(eachRow, Integer.MAX_VALUE);
		}
		
		differenceMatrix[0][0] = 0;
		
		PriorityQueue<Cell> queue = new PriorityQueue<Cell>((a, b) -> (a.difference - b.difference));
		
		boolean[][] visited = new boolean[m][n];
		queue.offer(new Cell(0, 0, differenceMatrix[0][0]));

		while (!queue.isEmpty()) {
			Cell top = queue.poll();
			visited[top.x][top.y] = true;
			
			if (top.x == m - 1 && top.y == n - 1) {
				return top.difference;
			}
			
			for (int[] move : moves) {
				int x = top.x + move[0], y = top.y + move[1];
				
				if (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && !visited[x][y]) {
					int currentDifference = Math.abs(heights[x][y] - heights[top.x][top.y]);
					int maxDifference = Math.max(currentDifference, differenceMatrix[top.x][top.y]);
					if (differenceMatrix[x][y] > maxDifference) {
						differenceMatrix[x][y] = maxDifference;
						queue.offer(new Cell(x, y, maxDifference));
					}
				}
			}
		}
		return differenceMatrix[m - 1][n - 1];
	}

	private static class Cell {
		int x;
		int y;
		int difference;

		Cell(int x, int y, int difference) {
			this.x = x;
			this.y = y;
			this.difference = difference;
		}
	}
}
