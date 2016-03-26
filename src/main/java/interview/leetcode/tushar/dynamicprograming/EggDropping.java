package interview.leetcode.tushar.dynamicprograming;

public class EggDropping
{
	public static void main(String[] args){
		System.out.println(numberOfMinAttempts(60, 3));
	}
	
	private static int numberOfMinAttempts(int floors, int eggs){
		
		int[][] board = new int[eggs+1][floors + 1];
		
		for(int i=1; i<=eggs; i++){
			for(int j=1; j<=floors; j++){
				if(i==1){
					board[i][j] = j;
				}
				else if(j<i){
					board[i][j] = board[i-1][j];
				}
				else{
					int k=1;
					int attempt = Integer.MAX_VALUE;
					while(k<=j){
						int temp = 1 + Math.max(board[i-1][k-1], board[i][j-k]);
						attempt = Math.min(attempt, temp);
						k++;
					}
					board[i][j] = attempt; 
				}
			}
		}
		
		return board[eggs][floors];
	}
}
