package interview.leetcode.prob;

/**
 * A train line going through a city has two routes, the regular route and the express route. Both routes go through the same n + 1 stops labeled from 0 to n. Initially, you start on the regular route at stop 0.

You are given two 1-indexed integer arrays regular and express, both of length n. regular[i] describes the cost it takes to go from stop i - 1 to stop i using the regular route, and express[i] describes the cost it takes to go from stop i - 1 to stop i using the express route.

You are also given an integer expressCost which represents the cost to transfer from the regular route to the express route.

Note that:

There is no cost to transfer from the express route back to the regular route.
You pay expressCost every time you transfer from the regular route to the express route.
There is no extra cost to stay on the express route.
Return a 1-indexed array costs of length n, where costs[i] is the minimum cost to reach stop i from stop 0.

Note that a stop can be counted as reached from either route.

 

Example 1:


Input: regular = [1,6,9,5], express = [5,2,3,10], expressCost = 8
Output: [1,7,14,19]
Explanation: The diagram above shows how to reach stop 4 from stop 0 with minimum cost.
- Take the regular route from stop 0 to stop 1, costing 1.
- Take the express route from stop 1 to stop 2, costing 8 + 2 = 10.
- Take the express route from stop 2 to stop 3, costing 3.
- Take the regular route from stop 3 to stop 4, costing 5.
The total cost is 1 + 10 + 3 + 5 = 19.
Note that a different route could be taken to reach the other stops with minimum cost.
Example 2:


Input: regular = [11,5,13], express = [7,10,6], expressCost = 3
Output: [10,15,24]
Explanation: The diagram above shows how to reach stop 3 from stop 0 with minimum cost.
- Take the express route from stop 0 to stop 1, costing 3 + 7 = 10.
- Take the regular route from stop 1 to stop 2, costing 5.
- Take the express route from stop 2 to stop 3, costing 3 + 6 = 9.
The total cost is 10 + 5 + 9 = 24.
Note that the expressCost is paid again to transfer back to the express route.
 

Constraints:

n == regular.length == express.length
1 <= n <= 105
1 <= regular[i], express[i], expressCost <= 105
Accepted
12,373
Submissions
15,808
 * 
 * Nov 8, 2024 - 10:25:21 PM
 * Jojo 
 */
public class MinimumCostUsingTheTrainLine {
	public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        //return topdown(regular, express, expressCost);
        return bottomup(regular, express, expressCost);
    }
    
    private long[] bottomup(int[] regular, int[] express, int expressCost){
        int len = regular.length;
        
        long[][] dp = new long[len][2];
        dp[0][0] = regular[0];
        dp[0][1] = expressCost + express[0]; // adding the express cost because the start is from [0][0]
        
        for(int i=1; i<len; i++){
            // regular lane
            dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + regular[i];
            
            // express lane
            // dp[i-1][0] -- represents regular lane thats why express cost is added.
            dp[i][1] = Math.min(dp[i-1][0] + expressCost, dp[i-1][1]) + express[i];
        }
        
        long[] result = new long[len];
        
        for(int i=0; i<len; i++){
            result[i] = Math.min(dp[i][0], dp[i][1]);
        }
        
        return result;
    }
    
    private long[] topdown(int[] regular, int[] express, int expressCost){
        int len = regular.length;
        
        Long[][] memo = new Long[len][2];
        
        dp(regular, express, expressCost, len-1, 0, memo);
        //dp(regular, express, expressCost, len-1, 1, memo);
        
        
        long[] result = new long[len];
        
        for(int i=0; i<len; i++){
            result[i] = memo[i][0];
        }
        
        return result;
    }
    
    private long dp(int[] regular, int[] express, int expressCost, int idx, int flag, Long[][] memo){
        if(idx < 0){
            return 0L;
        }
        
        if(memo[idx][flag] != null){
            return memo[idx][flag];
        }
        
        long v1 = (flag == 1 ? 0 : expressCost) + express[idx] + dp(regular, express, expressCost, idx - 1, 1, memo);
        long v2 = regular[idx] + dp(regular, express, expressCost, idx - 1, 0, memo);
        
        long min = Math.min(v1, v2);
        memo[idx][flag] = min;
        
        return min;
    }
}
