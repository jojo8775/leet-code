package interview.leetcode.concepts;

public class KnapSakProblem
{
	public static void main(String[] args){
		int[] weights = {1,3,4,5};
		int[] values = {1,4,5,7};
		
		System.out.println(findMaxValue(weights, values, 7));
	}
	
	private static int findMaxValue(int[] weights, int[] values, int totalWeight){
		int[][] grid = new int[weights.length+1][totalWeight + 1];
		
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[0].length; j++){
				if(j==0 || i==0){
					grid[i][j] = 0;
				}
				else if(weights[i-1] > j){
					grid[i][j] = grid[i-1][j];
				}
				else{
					grid[i][j] = Math.max(values[i-1] + grid[i-1][j-weights[i-1]], grid[i-1][j]);
				}
			}
		}
		
		return grid[weights.length][totalWeight];
	}
}
