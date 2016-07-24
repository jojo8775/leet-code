package interview.leetcode.prob;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
 * @author jojo
 *
 */
public class RangeSumQuery2D {
    private int[][] grid;

	public RangeSumQuery2D(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
			
		grid = new int[matrix.length][matrix[0].length];
		grid[0][0] = matrix[0][0];

		// assigning zeroth row
		for (int i = 1; i < matrix[0].length; i++) {
			grid[0][i] = matrix[0][i] + grid[0][i - 1];
		}
		
		// assigning zeroth col
		for (int i = 1; i < matrix.length; i++) {
			grid[i][0] = matrix[i][0] + grid[i - 1][0];
		}
		
		// assigning other cells
		for (int i = 1; i < matrix.length; i++) {
		    int rowSum = matrix[i][0];
			for (int j = 1; j < matrix[0].length; j++) {
			    rowSum += matrix[i][j];
				grid[i][j] = rowSum + grid[i - 1][j];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (this.grid == null)
			return 0;
		int top = row1, right = col2, bottom = row2, left = col1;
		
		int result = 0;

		if (top == 0 && left == 0) {
			result = grid[bottom][right];
		} 
		else if (top == 0) {
			result = grid[bottom][right] - grid[bottom][left - 1];

		}
		else if (left == 0) {
			result = grid[bottom][right] - grid[top - 1][right];
		} 
		else{
			result = grid[bottom][right] - grid[top - 1][right] - grid[bottom][left - 1] + grid[top - 1][left - 1];
		}

		return result;
	}

	public static void main(String[] args) {
		int[][] grid = new int[5][5];
		grid[0] = createArr(3, 0, 1, 4, 2);
		grid[1] = createArr(5, 6, 3, 2, 1);
		grid[2] = createArr(1, 2, 0, 1, 5);
		grid[3] = createArr(4, 1, 0, 1, 7);
		grid[4] = createArr(1, 0, 3, 0, 5);

		int[][] m = new int[0][0];

		new RangeSumQuery2D(m);

		System.out.println(new RangeSumQuery2D(grid).sumRegion(2, 1, 4, 3));

		// [[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]],sumRegion(2,1,4,3),sumRegion(1,1,2,2),sumRegion(1,2,2,4)
	}

	private static int[] createArr(int... a) {
		return a;
	}
}
