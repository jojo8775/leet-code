package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation: 
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can't be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 

Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

 * @author jojo
 * Sep 11, 2019 1:02:26 AM
 */
public class MyCalendarII {
	class MyCalendarTwo {
	    
	    List<int[]> intervals = new ArrayList<>();
	    
	    public MyCalendarTwo() {
	        
	    }
	    
	    public boolean book(int start, int end) {
	        TreeMap<Integer, Integer> overlaps = new TreeMap<>(); // need to return false if there is a conflict on overrides. 
	        
	        for(int[] interval : intervals){
	            // gets the overlap. 
	            int[] overlap = getOverlap(interval, start, end);
	            
	            // if the overlap is valid 
	            if(overlap[0] >= 0){
	                // does the overlap map has conflict
	                if(hasOverlap(overlaps, overlap[0], overlap[1])){
	                    return false;
	                }
	                
	                // store the last overlap interval.
	                overlaps.put(overlap[0], overlap[1]);
	            }
	        }
	        
	        // adding the new booking
	        intervals.add(new int[]{start, end});
	        return true;
	    }
	    
	    private int[] getOverlap(int[] interval, int start, int end){
	        if(end <= interval[0] || start >= interval[1]){
	            return new int[]{-1, -1};
	        }
	        
	        return new int[]{ Math.max(start, interval[0]), Math.min(end, interval[1]) };
	    }
	    
	    private boolean hasOverlap(TreeMap<Integer, Integer> overlaps, int start, int end){
	        Integer floorKey = overlaps.floorKey(start);
	        
	        // the floor key value is end. So technically previous overlap end cannot be greater than start
	        if(floorKey != null && overlaps.get(floorKey) > start){
	            return true;
	        }
	        
	        // The ceiling gives us the start time of the previous overlap, which cannot be less than current overlap end.
	        Integer ceilingKey = overlaps.ceilingKey(start);
	        if(ceilingKey != null && ceilingKey < end){
	            return true;
	        }
	        
	        return false;
	    }
	}
}
