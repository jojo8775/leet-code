package interview.leetcode.practice.round3.array;

import java.util.ArrayList;
import java.util.List;

public class MergeIntervalIV {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int idx = 0, len = intervals.size();
        
        while(idx < len && intervals.get(idx).end < newInterval.start){
            result.add(intervals.get(idx++));
        }
        
        if(idx < len && intervals.get(idx).start < newInterval.end){
            newInterval.start = Math.min(intervals.get(idx).start, newInterval.start);
        }
        
        while(idx < len && intervals.get(idx).end <= newInterval.end){
            idx++;
        }
        
        if(idx < len && intervals.get(idx).start <= newInterval.end){
            newInterval.end = Math.max(intervals.get(idx++).end, newInterval.end);
        }
        
        result.add(newInterval);
        
        while(idx < len){
            result.add(intervals.get(idx++));
        }
        
        return result;
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
