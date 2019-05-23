package interview.leetcode.practice;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
	public boolean canAttendMeetings(int[][] intervals) {
		
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}
				
				return o1[0] - o2[0];
			}
		});
		
		for(int i=1; i<intervals.length; i++) {
			if(intervals[i][0] < intervals[i][1]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(new MeetingRooms().canAttendMeetings(new int[][] {{0,30}, {5,10}, {15,20}}));
	}
}
