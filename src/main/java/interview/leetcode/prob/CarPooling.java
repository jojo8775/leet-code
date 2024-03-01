package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
 
 

Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
Accepted
9,589
Submissions
16,649
 * @author jojo
 * Aug 31, 2019 12:20:26 PM
 */
public class CarPooling {
	public boolean carPooling(int[][] trips, int capacity) {
        // tree map because need to parse the keys in ascending order.
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for(int[] trip : trips){
            // from point A to B at A remaining capacity decreases, and at B remaining capacity increases. 
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }
        
        for(int key : map.keySet()){
            capacity -= map.get(key);
            
            // if at any time the remaining capacity falls below zero, trip cannot be completed. 
            if(capacity < 0){
                break;
            }
        }
        
        // if the remaining capacity is >= 0, the trip is possible. 
        return capacity >= 0;
    }
	
	// merge interval logic. 
    public boolean carPooling_1(int[][] trips, int capacity) {
        Arrays.sort(trips, (a,b) -> a[1] - b[1]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        
        int bal = capacity;
        
        for(int i=0; i<trips.length; i++){
            while(!pq.isEmpty() && pq.peek()[2] <= trips[i][1]){
                bal += pq.poll()[0];
            }
            
            bal -= trips[i][0];
            
            if(bal < 0){
                return false;
            }
            
            pq.offer(trips[i]);
        }
        
        return true;
    }
}
