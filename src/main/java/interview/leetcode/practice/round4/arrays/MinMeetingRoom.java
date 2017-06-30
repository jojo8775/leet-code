package interview.leetcode.practice.round4.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinMeetingRoom {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length < 2) {
            return intervals.length;
        }

        Arrays.sort(intervals, (Interval a, Interval b) -> {
            if (a.start == b.start) {
                return a.end - b.end;
            }

            return a.start - b.start;
        });

        PriorityQueue<Interval> queue = new PriorityQueue<>((Interval a, Interval b) -> {
            if (a.end == b.end) {
                return a.start - b.start;
            }

            return a.end - b.end;
        });

        queue.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            Interval top = queue.poll();
            if (top.end <= intervals[i].start) {
                top.end = intervals[i].end;
            } else {
                queue.offer(intervals[i]);
            }
            queue.offer(top);
        }

        return queue.size();
    }

    private static class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) {
        Interval[] i = { new Interval(2, 15), new Interval(36, 45), new Interval(9, 29), new Interval(16, 23),
                new Interval(4, 9) };

        System.out.println(new MinMeetingRoom().minMeetingRooms(i));
    }
}
