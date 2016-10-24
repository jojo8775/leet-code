package interview.leetcode.prob.paid;

public class NumMatrix1 {
	int[][] grid = null, matrix = null;

    public NumMatrix1(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        
        this.matrix = matrix;
        grid = new int[matrix.length + 1][matrix[0].length];
        
        for(int i=1; i<grid.length; i++){
        	for(int j=0; j<grid[0].length; j++){
        		grid[i][j] = grid[i-1][j] + matrix[i-1][j];
        	}
        }
    }

    public void update(int row, int col, int val) {
        int prevVal = matrix[row][col];
        
        for(int i=row + 1; i<grid.length; i++){
            grid[i][col] = grid[i][col] - prevVal + val;
        }

        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int val = 0;
        
        for(int i=col1; i<=col2; i++){
            val = val + grid[row2+1][i] - grid[row1][i];
        }
        
        return val;
    }
    
    public static void main(String[] args){
    	NumMatrix1 nm = new NumMatrix1(new int[][] {{3,0,1,4,2}, {5,6,3,2,1}, {1,2,0,1,5}, {4,1,0,1,7}, {1,0,3,0,5}});
		System.out.println(nm.sumRegion(2, 1, 4, 3));
		nm.update(3,2,2);
		System.out.println(nm.sumRegion(2, 1, 4, 3));
	}
}
