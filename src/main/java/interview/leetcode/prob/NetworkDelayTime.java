package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
Accepted
198,462
Submissions
418,648
 * @author jojo
 * Dec 21, 2021 10:39:28 PM
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // create graph 
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for(int[] t : times){
            List<int[]> val = graph.get(t[0]);
            if(val == null){
                val = new ArrayList<>();
                graph.put(t[0], val);
            }
            
            val.add(new int[]{t[1], t[2]});
        }
        
        // map to store the min cost of each node from k
        Map<Integer, Integer> costMap = new HashMap<>();
        costMap.put(k, 0);
        
        // priority queue with smallest weight at top (node, weight)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int curNode = top[0], curCost = top[1];
            
            for(int[] nei : graph.getOrDefault(curNode, new ArrayList<>())){
                int neiCost = curCost + nei[1];
                if(costMap.get(nei[0]) == null || costMap.get(nei[0]) > neiCost){
                    costMap.put(nei[0], neiCost);
                    
                    pq.offer(new int[]{nei[0], neiCost});
                }
            }
        }
        
        // if there is a disjoint graph then return -1;
        if(costMap.size() != n){
            return -1;
        }
        
        return costMap.values().stream().mapToInt(x -> x).max().getAsInt();
    }
	
	public int networkDelayTime_1(int[][] times, int N, int K) {
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for(int i=0; i<times.length; i++) {
				graph.computeIfAbsent(times[i][0], k -> new HashMap<Integer, Integer>()); 
				graph.get(times[i][0]).put(times[i][1], times[i][2]);
		}
		
        Map<Integer, Integer> distanceMap = new HashMap<>();
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> a[0] - b[0]); // [0] = cost, [1] = curNode
        pq.offer(new int[] {0, K});
        
        while(!pq.isEmpty()) {
        	int[] top = pq.poll();
        	int curNode = top[1], curCost = top[0];
        	
        	if(distanceMap.containsKey(curNode) && distanceMap.get(curNode) < curCost) {
        		continue;
        	}
        	
        	for(Map.Entry<Integer, Integer> nei : graph.get(curNode).entrySet()) {
        		int cost = curCost + nei.getValue();
        		
        		if(distanceMap.containsKey(nei.getKey()) && distanceMap.get(nei.getKey()) <= cost ) {
        			continue;
        		}
        		
        		distanceMap.put(nei.getKey(), cost);
        		
        		pq.offer(new int[] {cost, nei.getKey()});
        	}
        }
        
        if(distanceMap.size() != N) {
        	return -1;
        }
        
        int max = 0;
        for(int cost : distanceMap.values()) {
        	max = Math.max(cost, max);
        }
        	
        return max;
    }
}
