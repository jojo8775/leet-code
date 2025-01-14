package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.

You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.

Return the maximum sum of values that you can receive by attending events.

 

Example 1:



Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
Output: 7
Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
Example 2:



Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
Output: 10
Explanation: Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
Example 3:



Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
Output: 9
Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.
 

Constraints:

1 <= k <= events.length
1 <= k * events.length <= 106
1 <= startDayi <= endDayi <= 109
1 <= valuei <= 106
Seen this question in a real interview before?
1/5
Yes
No
Accepted
78.4K
Submissions
128.2K
Acceptance Rate
61.2%
 * 
 * Jan 8, 2025 - 7:40:46 PM
 * Jojo 
 */
public class MinimumNumberOfEventsThatCanBeAttendedII {
	public int maxValue(int[][] events, int k) {
        return topdown(events, k);        
    }

    private int topdown(int[][] events, int k){
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        return dp(0, events, k, new Integer[events.length + 1][k + 1]);
    }

    private int dp(int idx, int[][] events, int k, Integer[][] memo){
        if(idx == events.length || k <= 0){
            return 0;
        }

        if(memo[idx][k] != null){
            return memo[idx][k];
        }

        // by not taking
        int byNotTaking = dp(idx+1, events, k, memo);

        // by taking
        int byTaking = events[idx][2] + dp(findNext(events[idx][1] + 1, idx + 1, events), events, k - 1, memo);

        return memo[idx][k] = Math.max(byNotTaking, byTaking);
    }

    private int findNext(int val, int beg, int[][] events){
        int end = events.length;

        while(beg < end){
            int mid = beg + (end - beg)/2;

            if(events[mid][0] < val){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }

        return beg;
    }
}
