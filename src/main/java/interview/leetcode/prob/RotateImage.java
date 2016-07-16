package interview.leetcode.prob;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * 
 * @author jojo
 *
 */
public class RotateImage {
	public void rotate(int[][] matrix) {
		int start = 0, end = matrix.length - 1;
		for (int layer = 0; layer < matrix.length / 2; layer++) {
			for (int i = start, count = 0; i <= end - 1; i++, count++) {
				int temp = matrix[start][start + count];

				matrix[start][start + count] = matrix[end - count][start];

				matrix[end - count][start] = matrix[end][end - count];

				matrix[end][end - count] = matrix[start + count][end];

				matrix[start + count][end] = temp;
			}

			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		// int[][] grid = new int [3][3];
		// grid[0] = createArr(1,1,1);
		// grid[1] = createArr(2,2,2);
		// grid[2] = createArr(3,3,3);

		int[][] grid = new int[4][4];
		grid[0] = createArr(1, 2, 3, 4);
		grid[1] = createArr(5, 6, 7, 8);
		grid[2] = createArr(9, 10, 11, 12);
		grid[3] = createArr(13, 14, 15, 16);

		new RotateImage().rotate(grid);

		print(grid);
	}

	private static int[] createArr(int... a) {
		return a;
	}

	private static void print(int[][] grid) {
		for (int[] arr : grid) {
			for (int i : arr) {
				System.out.print(i + ", ");
			}

			System.out.println();
		}
	}
}
