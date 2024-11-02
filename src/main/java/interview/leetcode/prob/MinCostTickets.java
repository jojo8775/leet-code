package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.

 

Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total you spent $17 and covered all the days of your travel.
 

Note:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000

 * @author jojo
 * Sep 2, 2019 1:20:30 AM
 */
public class MinCostTickets {
	public int mincostTickets_ite(int[] days, int[] costs) {
		// using queue so that the oldest ticket is at the top. 
        Queue<int[]> last7days = new LinkedList<>(), last30days = new LinkedList<>();
        
        int totalCost = 0;
        for(int i=0; i < days.length; i++){
        	// discarding expired 7days pass
            while(!last7days.isEmpty() && last7days.peek()[0] + 7 <= days[i]){
                last7days.poll();
            }
            
            last7days.offer(new int[]{days[i], totalCost + costs[1]});
            
            // discarding expired 30 days pass.
            while(!last30days.isEmpty() && last30days.peek()[0] + 30 <= days[i]){
                last30days.poll();
            }
            
            last30days.offer(new int[]{days[i], totalCost + costs[2]});
            
            // taking the min of daily pass and current valid 7 days or 30 days pass. 
            totalCost = Math.min(totalCost + costs[0], Math.min(last30days.peek()[1], last7days.peek()[1]));
        }
        
        return totalCost;
    }
	
	public int mincostTickets(int[] days, int[] costs) {
        //return topdown(days, costs);
        return bottomup(days, costs);
    }
    
    private int bottomup(int[] days, int[] costs){
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        
        int dayIdx = 0;
        for(int i=1; i<=lastDay; i++){
            if(days[dayIdx] > i){
                dp[i] = dp[i-1];
            }
            else{
                int oneDay = dp[i-1] + costs[0];
                int sevenDay = costs[1] + ((i - 7 >= 0) ? dp[i-7] : 0);
                int thirtyDay = costs[2] + ((i - 30 >= 0) ? dp[i-30] : 0);
                
                dp[i] = Math.min(oneDay, sevenDay);
                dp[i] = Math.min(dp[i], thirtyDay);
                
                dayIdx++;
            }
        }
        
        
        return dp[lastDay];
    }
    
    private int topdown(int[] days, int[] costs){
        return dp(days, 0, days[0], costs, new Integer[days.length]);
    }

    private int dp(int[] days, int idx, int dueDate, int[] costs, Integer[] memo){
        if(idx == days.length){
            return 0;
        }
        
        if(dueDate > days[idx]){
            return dp(days, idx + 1, dueDate, costs, memo);
        }
        
        if(memo[idx] != null){
            return memo[idx];
        }
        
        int oneDay = costs[0] + dp(days, idx + 1, days[idx] + 1, costs, memo);
        int sevenDay = costs[1] + dp(days, idx + 1, days[idx] + 7, costs, memo);
        int thirtyDay = costs[2] + dp(days, idx + 1, days[idx] + 30, costs, memo);
        
        int min = Math.min(oneDay, sevenDay);
        min = Math.min(min, thirtyDay);
        
        //System.out.println("idx: " + idx + "   cost: " + min);
        
        memo[idx] = min;
        
        return min;
    }
}
