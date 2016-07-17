package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * @author jojo
 *
 */
public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return list;
		}

		int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;

		while (top <= bottom && left <= right) {
			// going right
			for (int i = left; i <= right; i++) {
				list.add(matrix[top][i]);
			}

			// going down
			for (int i = top + 1; i <= bottom; i++) {
				list.add(matrix[i][right]);
			}

			// going left
			for (int i = right - 1; i >= left && top != bottom; i--) {
				list.add(matrix[bottom][i]);
			}

			// going up
			for (int i = bottom - 1; i > top && left != right; i--) {
				list.add(matrix[i][left]);
			}

			top++;
			right--;
			bottom--;
			left++;
		}

		return list;
	}
}
