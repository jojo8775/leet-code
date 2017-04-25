package interview.leetcode.prob.paid;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * @author jojo
 *
 */
public class ShortestDistance {
	public int shortestDistance(int[][] grid) {
		int[][] dist = new int[grid.length][grid[0].length];
		int walk = 1, minDist = -1;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// if the current cell is a building time to find the adjacent
				// free space
				if (grid[i][j] == 1) {
					// traversing to all 0 from first building, then to all -1
					// from second building and so on.
					minDist = bfs(dist, grid, --walk, i, j);
				}
			}
		}

		return minDist;
	}

	// Iterative BFS
	private int bfs(int[][] dist, int[][] grid, int walk, int i, int j) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] { i, j });

		int depth = 0, minDist = -1;
		int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		while (!queue.isEmpty()) {
			depth++;
			int lenght = queue.size();

			// this loop is required so that same level depth cell can be
			// visited
			for (int k = 0; k < lenght; k++) {
				int[] pos = queue.poll();
				for (int l = 0; l < 4; l++) {
					int y = pos[0] + delta[l][0];
					int x = pos[1] + delta[l][1];

					// if the adjacent cell is out of bound or unreachable
					if (y < 0 || y >= grid.length || x < 0 || x >= grid[y].length || grid[y][x] != walk) {
						continue;
					}

					grid[y][x] = walk - 1;

					// calculates distence from each building to other building.
					dist[y][x] += depth;

					queue.offer(new int[] { y, x });

					// This will always get the min val assigned.
					if (minDist < 0 || minDist > dist[y][x]) {
						minDist = dist[y][x];
					}
				}
			}
		}

		return minDist;
	}
	
	public static void main(String[] args){
	    int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
	    
	    System.out.println(new ShortestDistance().shortestDistance(grid));
	}
}
