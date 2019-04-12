package interview.leetcode.prob;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * @author jojo
 * Apr 11, 2019 8:47:26 PM
 */
public class BestTimeToBuyAndSellStockIV {
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
				profitDP[i][j] = Math.max(profitDP[i][j-1], prices[j] + fund); // if selling today is profitable vs buying yesterday
				fund = Math.max(fund, profitDP[i-1][j] - prices[j]); // is current transaction more profitable than previous 
			}
		}
		
		return profitDP[k][prices.length - 1];
	}
}
