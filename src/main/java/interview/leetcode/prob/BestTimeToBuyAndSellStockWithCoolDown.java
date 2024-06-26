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
	public int maxProfit(int[] prices){
	    // return maxProfit_stateMachine(prices);
        // return maxProfit_1(prices);
        // return topdown(prices);
        
        return bottomup(prices);
    }
    
    public int maxProfit_stateMachine(int[] prices) {
        int n = prices.length;
        if(n < 2){
            return 0;
        }
        
        int[] sell = new int[n], cooldown = new int[n];
        sell[1] = Math.max(0, prices[1] - prices[0]);
        
        int max = sell[1];
        
        for(int i=2; i<n; i++){
            int profit = prices[i] - prices[i-1];
            
            sell[i] = Math.max(sell[i-1] + profit, cooldown[i-2] + profit);
            cooldown[i] = Math.max(cooldown[i-1], sell[i-1]);
            
            max = Math.max(sell[i], cooldown[i]);
        }
        
        return max;
    }
    
    //generic solution when the cool down period is more than 1 
    public int maxProfit_1(int[] prices){
        int len = prices.length;
        
        int[] maxProfitArr = new int[len + 2]; // if the cool down days is n add n + 1 to the lenght 
        
        for(int i=len-1; i>=0; i--){
            int prevPrice = prices[i];
            
            int maxProfitAfterSelling = 0;
            
            for(int j=i+1; j<len; j++){
                // maxProfit[j + 2] because 1 cool down day and next portential sell can only be done after 1 day
                // if there was no cooldown then it would have been maxProfit[j + 1]
                int profitAfterSelling = prices[j] - prevPrice + maxProfitArr[j + 2]; 
                
                maxProfitAfterSelling = Math.max(maxProfitAfterSelling, profitAfterSelling);
            }
            
            int profitWithoutSelling = maxProfitArr[i+1];
            
            maxProfitArr[i] = Math.max(profitWithoutSelling, maxProfitAfterSelling);
        }
        
        return maxProfitArr[0];
    }
    
    private int topdown(int[] prices){
        return dp(prices, 0, 0, new Integer[prices.length][2]);
    }

    private int dp(int[] prices, int idx, int holding, Integer[][] memo){
        if(idx >= prices.length){
            return 0;
        }

        if(memo[idx][holding] != null){
            return memo[idx][holding];
        }

        int doingNothing = dp(prices, idx + 1, holding, memo);

        int doingSomething = 0;

        if(holding == 0){
            // buy 
            doingSomething = -prices[idx] + dp(prices, idx + 1, 1, memo);
        }
        else{
            // sell 
            doingSomething = prices[idx] + dp(prices, idx + 2, 0, memo);
        }

        memo[idx][holding] = Math.max(doingSomething, doingNothing);

        return memo[idx][holding];
    }
    
    private int bottomup(int[] prices){
        int[][] dp = new int[prices.length + 2][2]; // + 2 because the cool down time is 1 day. so for the recurrence, I have to add + 2 days after a stock is sold for next transaction.
        
        for(int idx=prices.length - 1; idx>=0; idx--){
            for(int holding = 0; holding < 2; holding++){
                int doingNothing = dp[idx + 1][holding];
                
                int doingSomething = 0;
                
                if(holding == 0){
                    // buy
                    doingSomething = -prices[idx] + dp[idx + 1][1];
                }
                else{
                    // sell
                    doingSomething = prices[idx] + dp[idx + 2][0];
                }
                
                dp[idx][holding] = Math.max(doingSomething, doingNothing);
            }
        }
        
        return dp[0][0];
    }
	
	
	/*
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

	public int maxProfit_genericCooldown(int[] prices){
        int len = prices.length;
        
        int[] maxProfitArr = new int[len + 2]; // if the cool down days is n add n + 1 to the lenght 
        
        for(int i=len-1; i>=0; i--){
            int prevPrice = prices[i];
            
            int maxProfitAfterSelling = 0;
            
            for(int j=i+1; j<len; j++){
                // maxProfit[j + 2] because 1 cool down day and next portential sell can only be done after 1 day
                // if there was no cooldown then it would have been maxProfit[j + 1]
                int profitAfterSelling = prices[j] - prevPrice + maxProfitArr[j + 2]; 
                
                maxProfitAfterSelling = Math.max(maxProfitAfterSelling, profitAfterSelling);
            }
            
            int profitWithoutSelling = maxProfitArr[i+1];
            
            maxProfitArr[i] = Math.max(profitWithoutSelling, maxProfitAfterSelling);
        }	
        
        return maxProfitArr[0];
    }
	
	public static void main(String[] args) {
		System.out.println(new BestTimeToBuyAndSellStockWithCoolDown().maxProfit(new int[] { 1, 2, 3, 0, 2 }));
	}
	*/
}
