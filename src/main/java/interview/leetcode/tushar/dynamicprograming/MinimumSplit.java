package interview.leetcode.tushar.dynamicprograming;

public class MinimumSplit
{
	public static void main(String[] args){
		System.out.println(findMinimumSplit("abcbm"));
	}
	
	private static int findMinimumSplit(String s){
		int[][] board = new int[s.length()][s.length()];

		for(int i=1; i<s.length(); i++){
			int k =0;
			for(int j=i; j<s.length(); j++){
				if(s.charAt(k) == s.charAt(j) && board[k+1][j-1] == 0){
					board[k][j] = 0;			
				}
				else{
					board[k][j] = 1 + Math.min(board[k+1][j], board[k][j-1]);
				}

				k++;
			}
		}
		
		return board[0][s.length() - 1];
	}
}
