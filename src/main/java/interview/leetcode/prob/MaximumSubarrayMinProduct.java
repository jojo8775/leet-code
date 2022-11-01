package interview.leetcode.prob;

import java.util.Stack;

public class MaximumSubarrayMinProduct {
	private int mod = (int) (1e9+7);
	
	public int maxSumMinProduct(int[] n) {
	    Stack<Integer> stack = new Stack<>();
	    
	    long dp[] = new long[n.length + 1], result = 0;
	    
	    for (int i = 0; i < n.length; ++i) {
	       dp[i + 1] = dp[i] + n[i];
	    }
	    
	    for (int i = 0; i <= n.length; ++i) {
	    	// maintaining a monotonic decreasing stack. 
	        while (!stack.empty() && (i == n.length || n[stack.peek()] > n[i])) {
	        	// since this is a monotonic decreasing stack the top index will be the smallest element index so far from left
	            int minIdex = stack.pop();
	            result = Math.max(result, (dp[i] - dp[stack.empty() ? 0 : stack.peek() + 1]) * n[minIdex]);
	        }
	        
	        stack.push(i);
	    }
	    
	    return (int)(result % mod);
	}
}
