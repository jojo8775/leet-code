package interview.leetcode.prob;

import java.util.Map;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

 * @author jojo
 *Feb 10, 20181:56:20 AM
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 0){
            return 0;
        }
        
        if(cost.length < 2){
            return cost[0];
        }
        
        int prev1 = cost[0], prev2 = cost[1];
        for(int i=2; i<cost.length; i++){
            int cur = Math.min(cost[i] + prev1, cost[i] + prev2);
            prev1 = prev2;
            prev2 = cur;
        }
        
        return Math.min(prev2, prev1);
    }
    
    public int minCostClimbingStairs_all_dp_concept(int[] cost) {
        // return topDown(cost, cost.length, new HashMap<Integer, Integer>());
        // return bottomUp(cost);
        return bottomUp_adv(cost);
    }
     
     private int bottomUp_adv(int[] cost){
         int prev = cost[0]; // state : cost from step i | Base case
         int cur = cost[1];  // state : cost from step i | Base case
         int len = cost.length;
         
         for(int i=2; i<len; i++){
             int next = cost[i] + Math.min(prev, cur); // relation
             prev = cur;
             cur = next;
         }
         
         return Math.min(prev, cur); // relation
     }
     
     private int bottomUp(int[] cost){
         int len = cost.length;
         int[] dp = new int[len + 1]; // state
         
         for(int i=2; i<=len; i++){
             int oneStepBack = cost[i - 1] + dp[i - 1]; 
             int twoStepBack = cost[i - 2] + dp[i - 2];
             
             dp[i] = Math.min(oneStepBack, twoStepBack); // relation
         }
         
         return dp[len];
     }
     
     private int topDown(int[] cost, int idx, Map<Integer, Integer> memo){
         if(idx <= 1){ // base case
             return 0;
         }
         
         if(memo.containsKey(idx)){ // memonization
             return memo.get(idx);
         }
         
         int oneStepBack = cost[idx - 1] + topDown(cost, idx - 1, memo); 
         int twoStepBack = cost[idx - 2] + topDown(cost, idx - 2, memo);
         
         int val = Math.min(oneStepBack, twoStepBack); // relation
         memo.put(idx, val);
         
         return val;
     }
}
