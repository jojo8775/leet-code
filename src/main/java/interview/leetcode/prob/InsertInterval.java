package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

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
		List<Interval> result = new ArrayList<Interval>();

		int idx = 0, len = intervals.size();

		// adding all intervals before newInterval
		while (idx < len && intervals.get(idx).end < newInterval.start) {
			result.add(intervals.get(idx++));
		}

		// adjusting the start time of the newInterval when there is an overlap
		if (idx < len && intervals.get(idx).start < newInterval.start) {
			newInterval.start = intervals.get(idx).start;
		}

		// merging all overlapping intervals
		while (idx < len && intervals.get(idx).start <= newInterval.end) {
			newInterval.end = Math.max(newInterval.end, intervals.get(idx++).end);
		}

		// adding the newInterval
		result.add(newInterval);

		// adding rest of the intervals
		while (idx < len) {
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
