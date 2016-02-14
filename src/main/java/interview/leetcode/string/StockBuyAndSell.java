package interview.leetcode.string;

public class StockBuyAndSell
{
	/**
	 * max profit with k transactions.
	 */
	private int findMaxProfit(int[] prices, int k){
		if(prices.length == 0){
			return 0;
		}
		
		if(k >= prices.length/2){
			int maxProfit = 0;
			for(int i=1; i<prices.length; i++){
				if(prices[i] > prices[i-1]){
					maxProfit += prices[i] - prices[i-1];
				}
			}
			
			return maxProfit;
		}
		
		int[][] profitDP = new int[k+1][prices.length];
		int fund = 0;
		
		for(int i=1; i<=k; i++){
			fund = - prices[0];
			for(int j=1; j<prices.length; j++){
				profitDP[i][j] = Math.max(profitDP[i][j-1], prices[j] + fund);
				fund = Math.max(fund, profitDP[i-1][j] - prices[j]);
			}
		}
		
		return profitDP[k][prices.length - 1];
	}
}
