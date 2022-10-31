package interview.leetcode.prob;

import java.util.Arrays;

/**
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.

You are given an integer n. There is originally an array consisting of n integers from 1 to n in ascending order, return the number of derangements it can generate. Since the answer may be huge, return it modulo 109 + 7.

 

Example 1:

Input: n = 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
Example 2:

Input: n = 2
Output: 1
 

Constraints:

1 <= n <= 106
Accepted
9,774
Submissions
23,361
 * @author jojo
 * Oct 30, 2022 11:52:53 AM
 */
public class FindTheDerangementOfAnArray {
	private int mod = (int)(1e9 + 7);
    public int findDerangement(int n) {
       return bottomUp(n);
       //return topDown(n);
    }
    
    private int bottomUp(int n){
        if(n < 2){
            return 0;
        }
        
        
        int[] dp = new int[n + 1];
        
        dp[0] = 1; // ease of calculation 
        
        dp[1] = 0; // base case: when there in only one element we cant do much about it
        
        dp[2] = 1; // base case: when there are two elements, only one way is possible. 
        
        for(int i=2; i<=n; i++){
            
            // state: after picking one element there are i-1 places to put it back
            long numberOfPlacesToPut = i - 1;
            
            // state: we have two options after picking an element. Swap with one of the i-1 element. If we do swap then we will be left with i-2 elements
            int numberOfwaysWithSwap = dp[i - 2];
        
            // state: If we do not swap then we will be left with i-1 elements
            int numberOfwaysWithoutSwap = dp[i - 1];
            
            // relation: 
            long val = (numberOfPlacesToPut * (numberOfwaysWithSwap + numberOfwaysWithoutSwap) % mod) % mod;
            
            dp[i] = (int) val;
        }
        
        return dp[n];
    }
    
    private int topDown(int n){
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        
        return dp(n, memo);
    }
    
    
    private int dp(int n, int[] memo){
        
        // base case 
        if(n <= 1){
            return 0;
        }
        
        // base case 
        if(n == 2){
            return 1;
        }
        
        if(memo[n] != -1){
            return memo[n];
        }
        
        // state: after picking one element there are n-1 places to put it back
        long numberOfPlacesToPut = n - 1;
        
        // state: we have two options after picking an element. Swap with one of the n-1 element. If we do swap then we will be left with n-2 elements
        int numberOfElementsWithSwap = n - 2;
        
        // state: If we do not swap then we will be left with n-1 elements
        int numberOfElementsWithoutSwap = n-1;
        
        // this is the relation
        long val = numberOfPlacesToPut * ((dp(numberOfElementsWithSwap, memo) + dp(numberOfElementsWithoutSwap, memo)) % mod) % mod;
        
        memo[n] = (int) val;
        return memo[n];
    }
}
