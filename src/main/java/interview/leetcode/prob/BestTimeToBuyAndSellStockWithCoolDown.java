package interview.leetcode.prob;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * @author jojo
 * Jul 7, 2019 11:16:29 PM
 */
public class BestTimeToBuyAndSellStockWithCoolDown {
	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}

		// stores the max for selling stock 
		int[] sell = new int[prices.length];
		
		// stores the max with cool-down day
		int[] coolDown = new int[prices.length];

		// first days transaction with selling the stock.
		sell[1] = Math.max(0, prices[1] - prices[0]);
		int max = sell[1];

		for (int i = 2; i < prices.length; i++) {
			int profit = prices[i] - prices[i - 1];

			// max for selling if previous day stock was bought, or if the previous day was a cool-down day.
			// (sell[i-1] + profit) represents if previous day a stock was bought.
			// (coolDown[i-2] + profit) represents if the previous day a stock was sold. 
			sell[i] = Math.max(sell[i - 1] + profit, coolDown[i - 2] + profit);
			
			// stored the max of previous cool-down or previous sell.  
			coolDown[i] = Math.max(coolDown[i - 1], sell[i - 1]);

			max = Math.max(coolDown[i], sell[i]);
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(new BestTimeToBuyAndSellStockWithCoolDown().maxProfit(new int[] { 1, 2, 3, 0, 2 }));
	}
}