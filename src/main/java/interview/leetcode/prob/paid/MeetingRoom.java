package interview.leetcode.prob.paid;

import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all
 * meetings.
 * 
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 * 
 * @author jojo
 *
 */
public class MeetingRoom {
	public boolean canAttendMeetings(Interval[] intervals) {
		// sorting the intervals based on begin date time
		Arrays.sort(intervals, (Interval i1, Interval i2) -> {
			return i1.start - i2.start;
		});

		// looking for an overlap
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i - 1].end > intervals[i].start) {
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
