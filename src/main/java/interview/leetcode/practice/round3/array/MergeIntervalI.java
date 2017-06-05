package interview.leetcode.practice.round3.array;

import java.util.Arrays;

public class MergeIntervalI {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (Interval a, Interval b) -> {
                if(a.start == b.start){
                    return a.end - b.end;
                }
                
                return a.start - b.start;
            });
            
        for(int i=1; i<intervals.length; i++){
            if(intervals[i-1].end > intervals[i].start){
                return false;
            }
        }
        
        return true;
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
