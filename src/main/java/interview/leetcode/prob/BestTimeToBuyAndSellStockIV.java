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
	
    private int topDown(int k, int[] prices) {
        int[][][] memo = new int[prices.length][k + 1][2];
        
        return dp(0, k, 0, prices, memo);
    }
    
    private int dp(int idx, int remainingTransactions, int holding, int[] prices, int[][][] memo){
        if(idx == prices.length || remainingTransactions == 0){
            return 0;
        }
        
        // memo
        if(memo[idx][remainingTransactions][holding] != 0){
            return memo[idx][remainingTransactions][holding];
        }
        
        int doNothing = dp(idx + 1, remainingTransactions, holding, prices, memo);
        int doSomething;
        
        if(holding == 1){
            // selling
            doSomething = prices[idx] + dp(idx + 1, remainingTransactions - 1, 0, prices, memo);
        }
        else{
            // buying
            doSomething = - prices[idx] + dp(idx + 1, remainingTransactions, 1, prices, memo);
        }
        
        // relation
        memo[idx][remainingTransactions][holding] = Math.max(doNothing, doSomething);
        
        return memo[idx][remainingTransactions][holding];
    }
    
    private int bottomUp(int k, int[] prices) {
        // states
        int[][][] dp = new int[prices.length + 1][k + 1][2];
        
        for(int idx=prices.length - 1; idx>=0; idx--){
            for(int remainingTransactions = 1; remainingTransactions <= k; remainingTransactions++){
                for(int holding = 0; holding < 2; holding++){
                    int doNothing = dp[idx + 1][remainingTransactions][holding];
                    
                    int doSomething;
                    
                    if(holding == 1){
                        // sell
                        doSomething = prices[idx] + dp[idx + 1][remainingTransactions - 1][0];
                    }
                    else{
                        // buy
                        doSomething = - prices[idx] + dp[idx + 1][remainingTransactions][1];
                    }
                    
                    // relation
                    dp[idx][remainingTransactions][holding] = Math.max(doSomething, doNothing);
                }
            }
        }
        
        return dp[0][k][0];
    }
}
