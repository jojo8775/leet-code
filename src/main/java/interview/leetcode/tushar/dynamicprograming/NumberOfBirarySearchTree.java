package interview.leetcode.tushar.dynamicprograming;

public class NumberOfBirarySearchTree
{
	public static void main(String[] args){
		System.out.println(findNumberOfTrees(4));
	}
	
	private static int findNumberOfTrees(int nodes){
		if(nodes <= 2){
			return 1;
		}
		
		int[] arr = new int[nodes+1];
		arr[0] = 1;
		arr[1] = 1;
		
		for(int i=2; i<=nodes; i++){
			int temp = 0;
			for(int j=1; j<=i; j++){
				temp += arr[j-1] * arr[i-j];
			}
			arr[i] = temp;
		}
		
		return arr[nodes];
	}
}
