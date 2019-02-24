package interview.leetcode.prob;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * @author jojo
 * Feb 24, 2019 11:50:49 AM
 */
public class BestTimeToBuyAndSellStockIII {
	public int maxProfit(int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        
        // this is a generic solution for K transaction. Hard-coded to support 2 transactions.
        // profit dp always stores the max profit which can be made 
		int[][] profitDP = new int[2+1][prices.length];
		
		// initial fund.  
		int fund = 0;
		
		for(int i=1; i<=2; i++){
			// need to buy first before selling. This may have roll-over funds from previous transaction if any.  
			fund = - prices[0];
			
			for(int j=1; j<prices.length; j++){
				// if selling stock at current price will earn more profit than previous days price.
				profitDP[i][j] = Math.max(profitDP[i][j-1], prices[j] + fund);
				
				// this determines the buying price. 
				fund = Math.max(fund, profitDP[i-1][j] - prices[j]);
			}
		}
		
		return profitDP[2][prices.length - 1];
    }
	
	public static void main(String[] args) {
		System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit(new int[] {3,3,5,0,0,3,1,4}));
	}
}
