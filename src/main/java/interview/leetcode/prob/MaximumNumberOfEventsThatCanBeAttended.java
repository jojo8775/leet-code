package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.

Return the maximum number of events you can attend.

 

Example 1:


Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
Example 2:

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4
 

Constraints:

1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105
Accepted
47,824
Submissions
144,121
 * @author jojo
 * Jan 25, 2022 8:21:36 PM
 */
public class MaximumNumberOfEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);
        
        int curStartDate = 0, result = 0, idx = 0, len = events.length;
        
        while(idx < len || !pq.isEmpty()){
            if(pq.isEmpty()){
                curStartDate = events[idx][0];
            }
            
            while(idx < len && events[idx][0] <= curStartDate){
                pq.offer(events[idx][1]);
                idx++;
            }
            
            pq.poll();
            curStartDate++;
            result++;
            
            while(!pq.isEmpty() && pq.peek() < curStartDate){
                pq.poll();
            }
        }
        
        return result;
    }
}
