package interview.leetcode.prob;

/**
 * Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * @author jojo
 * Feb 24, 2019 1:26:21 AM
 */
public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		int profit = 0, minBuyPrice = Integer.MAX_VALUE;
		for (int n : prices) {
			profit = Math.max(profit, n - minBuyPrice);
			minBuyPrice = Math.min(minBuyPrice, n);
		}
		
		return profit;
	}
}
