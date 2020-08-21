package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

 

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 

Constraints:

1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
intervals[i] != intervals[j] for all i != j
Accepted
11,184
Submissions
19,329
 * @author jojo
 * Aug 21, 2020  12:25:22 AM
 */
public class RemoveConveredIntervals {
	public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> {
            if(a[0] == b[0]){
                return b[1] - a[1];
            }
            
            return a[0] - b[0];
        });
        
        int count = 1;
        int[] prev = intervals[0];
        
        for(int i=1; i<intervals.length; i++){
            if(prev[1] >= intervals[i][1]){
                continue;
            }
            
            count++;
            prev = intervals[i];
        }
        
        return count;
    }
}
