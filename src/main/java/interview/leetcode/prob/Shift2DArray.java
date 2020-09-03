package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * 
Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] moves to grid[i][j + 1].
Element at grid[i][n - 1] moves to grid[i + 1][0].
Element at grid[m - 1][n - 1] moves to grid[0][0].
Return the 2D grid after applying shift operation k times.

 

Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]
Example 2:


Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 50
1 <= n <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
Accepted
18,777
Submissions
30,599
 * @author jojo
 * Sep 2, 2020  10:22:03 PM
 */
public class Shift2DArray {
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int m = grid.length, n = grid[0].length;
		int end = grid.length * grid[0].length;
		k %= end;

		if (k != 0) {
			// this idea is same as rotating an array. 
			reverse(grid, 0, end - 1);
			reverse(grid, 0, k - 1);
			reverse(grid, k, end - 1);
		}

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				row.add(grid[i][j]);
			}

			result.add(row);
		}

		return result;
	}

	private void reverse(int[][] grid, int start, int end) {
		int n = grid.length, m = grid[0].length;

		while (start < end) {
			int temp = grid[start / m][start % m];
			grid[start / m][start % m] = grid[end / m][end % m];
			grid[end / m][end % m] = temp;

			start++;
			end--;
		}
	}
}
