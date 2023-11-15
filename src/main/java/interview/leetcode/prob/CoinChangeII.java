package interview.leetcode.prob;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
Accepted
546,488
Submissions
864,063
 * @author jojo
 * Nov. 5, 2023 9:26:18 p.m.
 */
public class CoinChangeII {
	public int change(int amount, int[] coins) {
        //return bottomup(amount, coins);
        //return topdown(amount, coins);
        return bottomup_adv(amount, coins);
    }
    
    private int bottomup_adv(int amount, int[] coins){
        int len = coins.length;
        
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for(int j=0; j<len; j++){
            for(int i=1; i<=amount; i++){
                if(coins[j] <= i){
                    dp[i] += dp[i - coins[j]];
                }
            }
        }
        
        return dp[amount];
    }
    
    private int bottomup(int amount, int[] coins) {
        int len = coins.length;
        
        // states
        int[][] dp = new int[len + 1][amount + 1];
        
        for(int i=0; i<=len; i++){
            dp[i][0] = 1; // this is becase 0 amount can be made by any coin
        }
        
        for(int i=len-1; i>=0; i--){
            for(int j=1; j<=amount; j++){
                if(coins[i] > j){
                    dp[i][j] = dp[i+1][j];
                }
                else{
                    dp[i][j] = dp[i+1][j] + dp[i][j - coins[i]];
                }
            }
        }
        
        return dp[0][amount];
    }
    
    private int topdown(int amount, int[] coins){
        Integer[][] memo = new Integer[coins.length][amount + 1];
        return dp(amount, coins, 0, memo);
    }
    
    private int dp(int amount, int[] coins, int idx, Integer[][] memo){
        if(idx == coins.length){
            return 0;
        }
        
        if(amount == 0){
            return 1;
        }
        
        if(memo[idx][amount] != null){
            return memo[idx][amount];
        }
        
        int result = 0;
        if(coins[idx] <= amount){
            int including = dp(amount - coins[idx], coins, idx, memo);
            int excluding = dp(amount, coins, idx + 1, memo);
            
            result = including + excluding;
        }
        else{
            int excluding = dp(amount, coins, idx + 1, memo);
            result = excluding;
        }
        
        return memo[idx][amount] = result;
    }
}
