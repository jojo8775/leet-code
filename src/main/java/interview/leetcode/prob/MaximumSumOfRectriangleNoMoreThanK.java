package interview.leetcode.prob;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

 * @author jojo
 *
 */
public class MaximumSumOfRectriangleNoMoreThanK {
	public int maxSumSubmatrix(int[][] matrix, int k) {
		int y = matrix.length;
		int x = matrix[0].length;

		// rotating the matrix virtually
		if (y > x) {
			int temp = x;
			x = y;
			y = temp;
		}

		// array to store the summation of rows or cols depending on the matrix
		// size
		int[] arr = new int[x];
		int maxArea = Integer.MIN_VALUE;

		// runtime of = O(m*m*nlong(n)) where m = longer dimenson and n =
		// shorter dimension
		for (int left = 0; left < y; left++) {
			Arrays.fill(arr, 0);
			for (int right = left; right < y; right++) {
				for (int i = 0; i < x; i++) {
					int curVal = 0;
					if (matrix.length > matrix[0].length) {
						curVal = matrix[i][right];
					} else {
						curVal = matrix[right][i];
					}

					arr[i] += curVal;

					// terminating it incase we find k
					if (curVal == k || arr[i] == k) {
						return k;
					}
				}

				// This is same as Count-of-sum-range problem
				TreeSet<Integer> set = new TreeSet<Integer>();
				set.add(0);

				int curSum = 0;

				for (int i = 0; i < arr.length; i++) {
					curSum += arr[i];
					Integer val = set.ceiling(curSum - k);

					if (val != null) {
						maxArea = Math.max(maxArea, curSum - val);
					}

					set.add(curSum);
				}
			}
		}

		return maxArea;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[1][];
		matrix[0] = new int[] { 2, 2, 3 };

		// int[][] matrix = new int[3][];
		// matrix[0] = new int[] { 5, -4, -3, 4 };
		// matrix[1] = new int[] { -3, -4, 4, 5 };
		// matrix[2] = new int[] { 5, 1, 5, -4 };

		System.out.println(new MaximumSumOfRectriangleNoMoreThanK().maxSumSubmatrix(matrix, 1));
	}
}
