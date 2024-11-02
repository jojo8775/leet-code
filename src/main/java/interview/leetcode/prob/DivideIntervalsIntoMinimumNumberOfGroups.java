package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval [lefti, righti].

You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no two intervals that are in the same group intersect each other.

Return the minimum number of groups you need to make.

Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] and [5, 8] intersect.

 

Example 1:

Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
Output: 3
Explanation: We can divide the intervals into the following groups:
- Group 1: [1, 5], [6, 8].
- Group 2: [2, 3], [5, 10].
- Group 3: [1, 10].
It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
Example 2:

Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
Output: 1
Explanation: None of the intervals overlap, so we can put all of them in one group.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
1 <= lefti <= righti <= 106
Accepted
37,358
Submissions
72,720
 * 
 * Oct 11, 2024 - 8:51:53 PM
 * Jojo 
 */
public class DivideIntervalsIntoMinimumNumberOfGroups {
	// line sweep 
    public int minGroups(int[][] intervals) {
        TreeMap<Integer, Integer> tMap = new TreeMap<>((a,b) -> a - b);
        
        for(int[] interval : intervals){
            tMap.put(interval[0], tMap.getOrDefault(interval[0], 0) + 1);
            
            // +1 because the end time is inclusive for an inteval
            tMap.put(interval[1] + 1, tMap.getOrDefault(interval[1] + 1, 0) - 1);
        }
        
        int minCount = 0, count = 0;
        
        for(int key : tMap.keySet()){
            count += tMap.get(key);
            minCount = Math.max(minCount, count);
        }
        
        return minCount;
    }
    
    // priority queue 
    public int minGroups_1(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        
        int minGroup = 0;
        
        for(int[] interval : intervals){
            while(!pq.isEmpty() && pq.peek() < interval[0]){
                pq.poll();
            }
            
            pq.offer(interval[1]);
            minGroup = Math.max(minGroup, pq.size());
        }
        
        return minGroup;
    }
}
