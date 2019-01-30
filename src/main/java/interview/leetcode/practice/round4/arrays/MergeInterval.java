package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class MergeInterval {
	public List<Interval> merge(List<Interval> intervals) {
		intervals.sort((a,b) -> a.start - b.start);
		List<Interval> result = new ArrayList<>();
		Interval prevInterval = intervals.get(0);
		
		for(int i=1; i<intervals.size(); i++) {
			Interval curInterval = intervals.get(i);
			if(prevInterval.end > curInterval.start) {
				prevInterval.end = Integer.max(prevInterval.end, curInterval.end);
			}
			else {
				result.add(prevInterval);
				prevInterval = curInterval;
			}
		}
		
		result.add(prevInterval);
		
		return result;
	}

	public class Interval {
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
