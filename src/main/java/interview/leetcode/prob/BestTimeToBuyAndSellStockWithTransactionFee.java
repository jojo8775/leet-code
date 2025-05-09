package interview.leetcode.prob;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 

Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
Seen this question in a real interview before?
1/5
Yes
No
Accepted
431.2K
Submissions
618.9K
Acceptance Rate
69.7%
 * Dec 30, 2024 - 10:22:17 PM
 * Jojo 
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
	public int maxProfit_1(int[] prices, int fee) {
        // initially there is no profit, and we need to buy a stock.
        int cash = 0, holding = -prices[0];
        
        for(int i=1; i<prices.length; i++){
            // either we sold yesterday and today we dont do anything 
            // or we bought yesterday and we need to sell today. 
            cash = Math.max(cash, holding + prices[i] - fee);
            
            // either I hold the share from yesterday or I didnt hold any share
            holding = Math.max(holding, cash - prices[i]);
        }
        
        return cash;
    }  
    
    // this is same as the cooldown problem : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
    public int maxProfit(int[] prices, int fee){
        int len = prices.length;
        int[][] dp = new int[len + 1][2];

        for(int i=len-1; i>=0; i--){
            for(int holding=0; holding<2; holding++){
                int doNothing = dp[i+1][holding];

                int doSomething = 0;
                if(holding == 0){
                	// Buy
                    doSomething = -prices[i] + dp[i+1][1];
                }
                else{
                	// Sell
                    doSomething = prices[i] - fee + dp[i+1][0];
                }

                dp[i][holding] = Math.max(doNothing, doSomething);
            }
        }

        return dp[0][0];
    }
}
