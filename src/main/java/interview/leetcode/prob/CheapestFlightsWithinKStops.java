package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.

 * @author jojo
 * Jan 14, 2019 11:47:18 PM
 */
public class CheapestFlightsWithinKStops {
	// this approach is by using djkstras algorithm 
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        
        // populating the graph
        for(int[] flight : flights){
            graph[flight[0]][flight[1]] = flight[2];
        }
        
        // heapify based on the smallest cost.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        queue.offer(new int[]{0,0,src});
        
        Map<String, Integer> bestPrice = new HashMap<>();
        
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            int cost = top[0], k = top[1], place = top[2];
            
            if(k > K+1 || cost > bestPrice.getOrDefault( k + "-" + place, Integer.MAX_VALUE)){
                continue;
            }
            
            if(place == dst){
                return cost;
            }
            
            for(int nei=0; nei<n; nei++){
                if(graph[place][nei] > 0){
                    int newCost = cost + graph[place][nei];
                    if(newCost < bestPrice.getOrDefault(k + "-" + nei, Integer.MAX_VALUE)){
                        bestPrice.put((k + 1) + "-" + nei, newCost);
                        queue.offer(new int[]{newCost, k+1, nei});
                    }
                }
            }
        }
        
        return -1;
    }
}
