package interview.leetcode.tushar.dynamicprograming;

public class GamePicking
{
	public static void main(String[] args){
		int[] turnValues = {3,9,1,2};
		findMaxScore(turnValues);
	}
	
	private static void findMaxScore(int[] turnValues){
		
		Turn[][] board = new Turn[turnValues.length][turnValues.length];

		for(int i=0; i < turnValues.length; i++){
			board[i][i] = new Turn(turnValues[i], 0);
		}
		
		for(int i=1; i < turnValues.length; i++){
			int k = 0;
			for(int j=i; j<turnValues.length; j++){
					int firstMaxPick = turnValues[k] + board[k+1][j].secondTurn;
					int secondMaxPick = turnValues[j] + board[k][j-1].secondTurn;
					
					if(firstMaxPick > secondMaxPick){
						board[k][j] = new Turn(firstMaxPick, board[k+1][j].firstTurn);
					}
					else{
						board[k][j] = new Turn(secondMaxPick, board[k][j-1].firstTurn);
					}
					
					k++;
			}
		}
		
		System.out.println(board[0][turnValues.length - 1].firstTurn);
		
	}
	
	private static class Turn
	{
		public int firstTurn;
		public int secondTurn;
		
		public Turn(int firstTurn, int secondTurn){
			this.firstTurn = firstTurn;
			this.secondTurn = secondTurn;
		}
	}
}