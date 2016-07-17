package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].


 * @author jojo
 *
 */
public class MergeInterval {
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() < 2) {
			return intervals;
		}

		Collections.sort(intervals, new IntervalComparator());

		List<Interval> result = new ArrayList<Interval>();
		Interval prev = intervals.get(0);

		for (int i = 1; i < intervals.size(); i++) {
			if (prev.end < intervals.get(i).start) {
				result.add(prev);
				prev = intervals.get(i);
			} else {
				prev.end = Math.max(prev.end, intervals.get(i).end);
			}
		}

		result.add(prev);
		return result;
	}

	private static class IntervalComparator implements Comparator<Interval> {
		public int compare(Interval i1, Interval i2) {
			if (i1 == i2) {
				return 0;
			} else if (i1 == null) {
				return 1;
			} else if (i2 == null) {
				return -1;
			} else if (i1.start < i2.start) {
				return -1;
			} else if (i1.start > i2.start) {
				return 1;
			}

			return 0;
		}
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
