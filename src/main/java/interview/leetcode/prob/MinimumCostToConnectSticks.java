package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.

 

Example 1:

Input: sticks = [2,4,3]
Output: 14
Example 2:

Input: sticks = [1,8,3,5]
Output: 30
 

Constraints:

1 <= sticks.length <= 10^4
1 <= sticks[i] <= 10^4
Accepted
2,030
Submissions
3,511
 * @author jojo
 *Sep 1, 201910:26:36 AM
 */
public class MinimumCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);
        
        for(int s : sticks){
            pq.offer(s);
        }
        
        int totalCost = 0;
        while(pq.size() > 1){
            int cost = pq.poll() + pq.poll();
            totalCost += cost;
            pq.offer(cost);
        }
        
        return totalCost;
    }
}
