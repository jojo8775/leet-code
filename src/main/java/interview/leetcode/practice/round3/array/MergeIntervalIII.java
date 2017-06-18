package interview.leetcode.practice.round3.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeIntervalIII {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length < 2){
            return intervals.length;
        }
        
        Arrays.sort(intervals, (Interval a, Interval b) -> {
                if(a.start == b.start){
                    return a.end - b.end;
                }
                
                return a.start - b.start;
            });
        
        PriorityQueue<Interval> queue = new PriorityQueue<>((Interval a, Interval b) -> {
            if(a.end == b.end){
                return a.start - b.start;
            }
            
            return a.end - b.end;
        });
        
        queue.offer(intervals[0]);
        
        for(int i=1; i<intervals.length; i++){
            Interval top = queue.poll();
            if(top.end > intervals[i].start){
                queue.offer(intervals[i]);
            }
            else{
                top.end = Math.max(top.end, intervals[i].end);
            }
            
            queue.offer(top);
        }
        
        return queue.size();
    }
    
    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
