package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
	public int networkDelayTime(int[][] times, int N, int K) {
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
