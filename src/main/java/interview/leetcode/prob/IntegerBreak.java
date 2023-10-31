package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

 * @author jojo
 *
 */
public class IntegerBreak {
	public int integerBreak(int n) {
        //return topDown(n);
        return bottomUp(n);
    }
    
    private int topDown(int n){
        if(n <= 3){
            // only way to break 
            // 3 -> 2 * 1 = 2
            // 2 -> 1 * 1 = 1
            return n-1;
        }
        
        Map<Integer, Integer> memo = new HashMap<>();
        return dp(n, memo);
    }
    
    private int dp(int n, Map<Integer, Integer> memo){
        // base case.
        if(n <= 3){
            // this is because no need to break 2 and 3. The result will only decrease.
            return n;
        }
        
        if(memo.containsKey(n)){
            return memo.get(n);
        }
        
        int max = 0;
        for(int i=2; i<n; i++){
            // relation
            max = Math.max(max, i * dp(n-i, memo));
        }
        
        memo.put(n, max);
        return max;
    }
    
    private int bottomUp(int n){
        if(n <= 3){
            // only way to break 
            // 3 -> 2 * 1 = 2
            // 2 -> 1 * 1 = 1
            return n-1;
        }
        
        // states
        int[] dp = new int[n+1];
        
        // base cases. 
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        
        for(int i=1; i<=n; i++){
            for(int j=2; j<i; j++){
                // relation 
                dp[i] = Math.max(dp[i], j * dp[i-j]);    
            }
        }
        
        return dp[n];
    }
	
	public int integerBreak_old(int n) {
		// this can be solved by dynamic programming memorization
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i + 1; j++) {
				if (i + j <= n) {
					// using the memorization of the dynamic programming
					dp[i + j] = Math.max(Math.max(dp[i], i) * Math.max(dp[j], j), dp[i + j]);
				}
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(new IntegerBreak().integerBreak(5));
	}
}
