package interview.leetcode.prob.paid;

public class NumMatrix {
	int[][] grid = null, matrix= null;

	public NumMatrix(int[][] matrix) {
		grid = new int[matrix.length][matrix[0].length];
		this.matrix = matrix;
		process();
	}

	private void process() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int left = 0, up = 0, diagonal = 0;
				if (i != 0 && j != 0) {
					diagonal = grid[i - 1][j - 1];
				}

				if (i != 0) {
					left = grid[i - 1][j];
				}

				if (j != 0) {
					up = grid[i][j - 1];
				}

				grid[i][j] = matrix[i][j] + left + up - diagonal;
			}
		}
	}

	public void update(int row, int col, int val) {
		int prevVal = matrix[row][col];
        
        for(int i=row; i<grid.length; i++){
            for(int j=col; j<grid[i].length; j++){
                grid[i][j] = grid[i][j] - prevVal + val;
            }
        }
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int left = 0, up = 0, diagonal = 0;
		if (col1 > 0) {
			left = grid[row2][col1 - 1];
		}

		if (row1 > 0) {
			up = grid[row1 - 1][col2];
		}

		if (col1 > 0 && row1 > 0) {
			diagonal = grid[row1 - 1][col1 - 1];
		}

		return grid[row2][col2] - left - up + diagonal;
	}
	
	public static void main(String[] args){
		NumMatrix nm = new NumMatrix(new int[][] {{3,0,1,4,2}, {5,6,3,2,1}, {1,2,0,1,5}, {4,1,0,1,7}, {1,0,3,0,5}});
		System.out.println(nm.sumRegion(2, 1, 4, 3));
		nm.update(3,2,2);
		System.out.println(nm.sumRegion(2, 1, 4, 3));
	}
}
