package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.
NOTE: input types have been changed on June 17, 2019. Please reset to default code definition to get new method signature.

 

Accepted
18,551
Submissions
29,653
 * @author jojo
 * Sep 10, 2019 12:19:44 AM
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for(int i=0; i<schedule.size(); i++){
            Interval interval = schedule.get(i).get(0);
            pq.offer(new int[]{interval.start, interval.end, i, 0});
        }
        
        int prevEnd = pq.peek()[0];
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            
            if(top[3] + 1 < schedule.get(top[2]).size()){
                Interval interval = schedule.get(top[2]).get(top[3] + 1);
                pq.offer(new int[]{interval.start, interval.end, top[2], top[3] + 1});
            }
            
            if(top[0] > prevEnd){
                result.add(new Interval(prevEnd, top[0]));
            }
            
            prevEnd = Math.max(prevEnd, top[1]);
        }
        
        return result;
    }
    
    private static class Interval{
    	int start, end;
    	
    	public Interval(int start, int end) {
    		this.start = start;
    		this.end = end;
    	}
    }
}
