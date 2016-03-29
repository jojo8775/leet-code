package interview.leetcode.tushar.dynamicprograming;

public class OptimalBinarySearchTree
{
	public static void main(String[] args){
		int[] keys = {4,2,6,3};
		System.out.println(findCost(keys));
	}
	
	private static int findCost(int[] keys){
		
		int[][] arr = new int[keys.length][keys.length];
		
		for(int i=0; i<keys.length; i++){
			arr[i][i] = keys[i];
		}
		
		for(int i=1; i<arr.length; i++){
			
			int k=0;
			for(int j=i; j<arr.length; j++){
				int cost = Integer.MAX_VALUE, count = k;
				while(count <= j){
//					int tempCost = 2*(count - 1 < 0 ? 0 : arr[][]) + keys[k] + 2*(k+1>j? 0 : arr[k+1][j]);
//					cost = Math.min(cost, tempCost);
				}
				
				arr[i][j] = cost;
				k++;
			}
		}
		
		return arr[0][keys.length - 1];
	}
}
