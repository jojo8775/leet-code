package interview.leetcode.prob.paid;

/**
 * You have n dice, and each die has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.
Example 2:

Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:

Input: n = 30, k = 30, target = 500
Output: 222616187
Explanation: The answer must be returned modulo 109 + 7.
 

Constraints:

1 <= n, k <= 30
1 <= target <= 1000
Accepted
204,820
Submissions
369,326
 * @author jojo
 * Dec 25, 2023 9:59:30 PM
 */
public class NumberOfDiceRollsWithTargetSum2 {
	public int numRollsToTarget(int n, int k, int target) {
        // return topDown(n, k, target);
        return bottomUp(n, k, target);
    }
    
    private int mod = (int)(1e9 + 7);
    
    private int bottomUp(int n, int k, int target){
        // states 
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<= target; j++){
                
                for(int dieFace = 1; dieFace <= k; dieFace++){
                    // base case: if the current target is smaller than die face then break;
                    if(dieFace > j){
                        break;
                    }
                    
                    // relationship
                    dp[i][j] += dp[i-1][j - dieFace];
                    dp[i][j] %= mod;
                }
            }
        }
        
        
        return dp[n][target];
    }
    
    private int topDown(int n, int k, int target){
        Integer[][] memo = new Integer[n + 1][target + 1];
        
        return dp(n, k, target, memo);
    }
    
    private int dp(int n, int k, int target, Integer[][] memo){
        // base case: we could make a target then it is a way
        if(n == 0 && target == 0){
            return 1;
        }
        
        // if ran out of dice or target is negative then there is no wau
        if(n <= 0 || target <= 0){
            return 0;
        }
        
        // maintaining states for easier compute
        if(memo[n][target] != null){
            return memo[n][target];
        }
        
        int count = 0;
        for(int i=1; i<=k; i++){
            count += dp(n - 1, k, target - i, memo);
            count %= mod;
        }
        
        return memo[n][target] = count;
    }
}
