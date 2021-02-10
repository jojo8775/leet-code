package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given an array points representing integer coordinates of some points
 * on a 2D-plane, where points[i] = [xi, yi].
 * 
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan
 * distance between them: |xi - xj| + |yi - yj|, where |val| denotes the
 * absolute value of val.
 * 
 * Return the minimum cost to make all points connected. All points are
 * connected if there is exactly one simple path between any two points.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]] Output: 20 Explanation:
 * 
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points. Example 2:
 * 
 * Input: points = [[3,12],[-2,5],[-4,1]] Output: 18 Example 3:
 * 
 * Input: points = [[0,0],[1,1],[1,0],[-1,1]] Output: 4 Example 4:
 * 
 * Input: points = [[-1000000,-1000000],[1000000,1000000]] Output: 4000000
 * Example 5:
 * 
 * Input: points = [[0,0]] Output: 0
 * 
 * 
 * Constraints:
 * 
 * 1 <= points.length <= 1000 -106 <= xi, yi <= 106 All pairs (xi, yi) are
 * distinct. Accepted 12,836 Submissions 24,971
 * 
 * @author jojo Feb 8, 2021 10:35:51 PM
 */
public class MinCostToConnectAllPoints {
	public int minCostConnectPoints(int[][] points) {
		// this is using Kruskal's algo.  
		int n = points.length, sum = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				pq.offer(new int[] { findLen(points, i, j), i, j });
			}
		}

		int[] parent = new int[n + 1];

		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}

		while (n > 1) {
			int[] top = pq.poll();
			int p1 = findParent(parent, top[1]);
			int p2 = findParent(parent, top[2]);

			if (p1 != p2) {
				n--;
				parent[p1] = p2;
				sum += top[0];
			}
		}

		return sum;
	}

	private int findLen(int[][] points, int a, int b) {
		return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
	}

	private int findParent(int[] parent, int idx) {
		while (parent[idx] != idx) {
			parent[idx] = parent[parent[idx]];
			idx = parent[idx];
		}

		return idx;
	}
}
