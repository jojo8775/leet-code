package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 * 
 * @author jojo
 *
 */
public class MaximalRectriangle {
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}

		int maxArea = 0;
		int[] col = new int[matrix.length];

		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < col.length; j++) {
				if ((matrix[j][i] - '0') == 0) {
					col[j] = 0;
				} else {
					col[j] += (matrix[j][i] - '0');
				}
			}

			maxArea = Math.max(maxArea, getMaxArea(col));
		}

		return maxArea;
	}

	// this is same as max rectriangle area of a histogram
	private int getMaxArea(int[] col) {
		int maxArea = 0, idx = 0;
		Stack<Integer> stack = new Stack<Integer>();

		while (idx < col.length) {
			if (stack.isEmpty() || col[stack.peek()] <= col[idx]) {
				stack.push(idx++);
			} else {
				while (!stack.isEmpty() && col[stack.peek()] > col[idx]) {
					int topIdx = stack.pop();

					if (stack.isEmpty()) {
						maxArea = Math.max(maxArea, col[topIdx] * idx);
					} else {
						maxArea = Math.max(maxArea, col[topIdx] * (idx - (stack.peek() + 1)));
					}
				}
			}
		}

		while (!stack.isEmpty()) {
			int topIdx = stack.pop();

			if (stack.isEmpty()) {
				maxArea = Math.max(maxArea, col[topIdx] * idx);
			} else {
				maxArea = Math.max(maxArea, col[topIdx] * (idx - (stack.peek() + 1)));
			}
		}

		return maxArea;
	}
}
