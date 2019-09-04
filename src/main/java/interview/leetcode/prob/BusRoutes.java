package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.
Accepted
24,205
Submissions
59,611
 * @author jojo
 * Sep 3, 2019 11:14:39 PM
 */
public class BusRoutes {
	 public int numBusesToDestination(int[][] routes, int S, int T) {
	        int busCount = 0;
	        if(S == T){
	            return busCount;
	        }
	        
	        Map<Integer, List<Integer>> busMap = new HashMap<>();
	        
	        for(int bus=0; bus<routes.length; bus++){
	            for(int stop : routes[bus]){
	                busMap.computeIfAbsent(stop, v -> new ArrayList<Integer>()).add(bus);
	            }
	        }
	        
	        Queue<Integer> queue = new LinkedList<>();
	        queue.offer(S);
	        
	        Set<Integer> visitedBus = new HashSet<>();
	        
	        while(!queue.isEmpty()){
	            for(int len = queue.size() - 1; len >= 0; len--){
	                int topStop = queue.poll();

	                if(topStop == T){
	                    return busCount;
	                }

	                for(int bus : busMap.get(topStop)){
	                    if(visitedBus.add(bus)){
	                        for(int stop : routes[bus]){
	                            queue.offer(stop);
	                        }
	                    }
	                }
	            }
	            
	            busCount++;
	        }
	        
	        return -1;
	    }
}
