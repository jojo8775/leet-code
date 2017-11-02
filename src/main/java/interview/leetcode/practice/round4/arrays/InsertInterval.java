package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].


 * @author jojo
 *
 */
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        int i = 0, len = intervals.size();
        while (i < len) {
            if (intervals.get(i).start < newInterval.start && intervals.get(i).end < newInterval.start) {
                result.add(intervals.get(i));
            } else {
                break;
            }

            i++;
        }

        if (i == len) {
            result.add(newInterval);
        } else {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            while (i < len) {
                if (intervals.get(i).start <= newInterval.end) {
                    newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
                } else {
                    break;
                }

                i++;
            }

            result.add(newInterval);

            while (i < len) {
                if (intervals.get(i).start > newInterval.end) {
                    result.add(intervals.get(i));
                }

                i++;
            }
        }

        return result;
    }

    private static class Interval {
        int start;
        int end;
    }
}
