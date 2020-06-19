package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.

 

Example 1:

Input: mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
Output: [2,0,3]
Explanation: 
The number of soldiers for each row is: 
row 0 -> 2 
row 1 -> 4 
row 2 -> 1 
row 3 -> 2 
row 4 -> 5 
Rows ordered from the weakest to the strongest are [2,0,3,1,4]
Example 2:

Input: mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
Output: [0,2]
Explanation: 
The number of soldiers for each row is: 
row 0 -> 1 
row 1 -> 4 
row 2 -> 1 
row 3 -> 1 
Rows ordered from the weakest to the strongest are [0,2,3,1]
 

Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
Accepted
23,271
Submissions
34,024
 * @author jojo
 * Jun 18, 2020  10:29:50 PM
 */
public class KWeakestRowInAMatrix {
	public int[] kWeakestRows(int[][] mat, int k) {
		if (mat.length == 0 || mat[0].length == 0) {
			return new int[] {};
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			}

			return a[0] - b[0];
		});

		int width = mat[0].length;
		for (int i = 0; i < mat.length; i++) {
			int left = 0, right = width;
			while (left < right) {
				int mid = left + (right - left) / 2;

				if (mat[i][mid] == 0) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}

			pq.offer(new int[] { left, i });
		}

		int[] result = new int[k];

		for (int i = 0; i < k && !pq.isEmpty(); i++) {
			result[i] = pq.poll()[1];
		}

		return result;
	}
}
