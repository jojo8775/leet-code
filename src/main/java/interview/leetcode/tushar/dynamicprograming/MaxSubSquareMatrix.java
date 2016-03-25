package interview.leetcode.tushar.dynamicprograming;

public class MaxSubSquareMatrix
{
	public static void main(String[] args){
		int[][] matrix = new int[4][];
		matrix[0] = createArr(0,0,1,1,1);
		matrix[1] = createArr(1,0,1,1,1);
		matrix[2] = createArr(0,1,1,1,1);
		matrix[3] = createArr(1,0,1,1,1);
		
		findMaxSubSquare(matrix);
	}
	private static int[] createArr(int... arg){
		return arg;
	}
	
	private static void findMaxSubSquare(int[][] matrix){
		int[][] grid = new int[matrix.length][matrix[0].length];
		
		int maxSubMatrix = 0;
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				if(i==0 || j==0){
					grid[i][j] = matrix[i][j];
				}
				else{
					int temp = Math.min(grid[i][j-1], grid[i-1][j]);
					temp = Math.min(temp, grid[i-1][j-1]);
					
					grid[i][j] = temp + matrix[i][j];
					maxSubMatrix = Math.max(maxSubMatrix, grid[i][j]);
				}
			}
		}
		
		System.out.println(maxSubMatrix);
	}
}
