package interview.leetcode.tushar.dynamicprograming;

public class CoinChanging
{
	public static void main(String[] args){
		int[] coins = {1,2,3};
		System.out.println(findNumberOfWays(coins, 5));
	}
	
	private static int findNumberOfWays(int[] coins, int sum){
		int arr[][] = new int[coins.length+1][sum+1];
		
		for(int i=1; i <= coins.length; i++){
			for(int j=0; j<= sum; j++){
				if(j==0){
					arr[i][j] = 1;
				}
				else if(j >= coins[i-1]){
					int numberOfWaysWitoutTakingCoin = arr[i-1][j];
					int numberOfWaysWithTakingCoin = arr[i][j-coins[i-1]];
					arr[i][j] = numberOfWaysWitoutTakingCoin + numberOfWaysWithTakingCoin; 
				}
				else{
					arr[i][j] = arr[i-1][j];
				}
			}
		}
		
		return arr[coins.length][sum];
	}
}
