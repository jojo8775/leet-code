package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.

You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.

Return an array containing the answers to the queries.

 

Example 1:

Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
Output: [3,3,1,4]
Explanation: The queries are processed as follows:
- Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
- Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
- Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
- Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
Example 2:

Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
Output: [2,-1,4,6]
Explanation: The queries are processed as follows:
- Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
- Query = 19: None of the intervals contain 19. The answer is -1.
- Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
- Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 

Constraints:

1 <= intervals.length <= 105
1 <= queries.length <= 105
intervals[i].length == 2
1 <= lefti <= righti <= 107
1 <= queries[j] <= 107
Accepted
11,714
Submissions
24,769

 * @author jojo
 * Sep 30, 2022 3:05:09 PM
 */
public class MinimumIntervalToIncludeEachQuery {
	  public int[] minInterval(int[][] intervals, int[] queries) {
	        int[][] queryWithIndex = new int[queries.length][2];
	        
	        // this is needed to preserve the original index of queries 
	        for(int i=0; i<queries.length; i++){
	            queryWithIndex[i] = new int[]{queries[i], i};
	        }
	        
	        // sorting query and intervals so that we can get the closest overlapping intervals for a query
	        Arrays.sort(queryWithIndex, (a,b) -> a[0] - b[0]);
	        Arrays.sort(intervals, (a,b) -> (a[0]- b[0]));
	        
	        int[] result = new int[queryWithIndex.length];
	        
	        // min heap on the duration of any interval.
	        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - a[0]) - (b[1] - b[0]));
	        
	        for(int i=0, j=0; i<queryWithIndex.length; i++){
	            int query = queryWithIndex[i][0];
	            int queryIdx = queryWithIndex[i][1];
	            
	            // loading up all the internals which starts earlier to a query
	            while(j < intervals.length && intervals[j][0] <= query){
	                pq.offer(intervals[j++]);
	            }
	            
	            // removing the ones which ends before the current query
	            while(!pq.isEmpty() && pq.peek()[1] < query){
	                pq.poll();
	            }
	            
	            // since the heap will give the smallest internal at top, peek() will give the best answere.
	            result[queryIdx] = pq.isEmpty() ? -1 : (pq.peek()[1] - pq.peek()[0] + 1);
	        }
	                    
	        return result;
	    }
}
