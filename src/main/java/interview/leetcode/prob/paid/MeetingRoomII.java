package interview.leetcode.prob.paid;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms
 * required.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (Interval i1, Interval i2) -> {
            return i1.start - i2.start;
        });

        // this heap is used to check if there is any conflict
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>((a, b) -> a.end - b.end);
        heap.offer(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            Interval top = heap.poll();

            // represents there is a conflict
            if (top.end > intervals[i].start) {
                heap.offer(intervals[i]);
            }

            // there is no conflict so this meeting does not need new room.
            else {
                top.end = intervals[i].end;
            }

            // putting back the top.
            heap.offer(top);
        }

        // this will give the maximum number of rooms required
        return heap.size();
    }

    public static class Interval {
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

    public static void main(String[] args) {
        System.out.println(new MeetingRoomII().minMeetingRooms(new Interval[] { new Interval(0, 30),
                new Interval(5, 15), new Interval(35, 45), new Interval(40, 45) }));
    }
}
