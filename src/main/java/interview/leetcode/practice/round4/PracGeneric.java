package interview.leetcode.practice.round4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PracGeneric {
	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, List<int[]>> graph = new HashMap<>();
		
		for(int[] t : times) {
			graph.computeIfAbsent(t[0], v -> new ArrayList<int[]>());
			graph.get(t[0]).add(new int[] {t[1], t[2]});
			
			graph.computeIfAbsent(t[1], v -> new ArrayList<int[]>());
			graph.get(t[1]).add(new int[] {t[0], t[2]});
		}
		
		var costMap = new HashMap<Integer, Integer>();
		var pq = new PriorityQueue<int[]>((a,b) -> a[1] - b[1]);
		pq.offer(new int[] {k, 0});
		
		while(!pq.isEmpty()) {
			int[] top = pq.poll();
			
			for(int[] nei: graph.getOrDefault(top[0], new ArrayList<int[]>())) {
				int nextCost = top[1] + nei[1];
				
				if(costMap.get(nei[0]) == null || costMap.get(nei[0]) > nextCost) {
					costMap.put(nei[0], nextCost);
					
					pq.offer(new int[] {nei[0], nextCost});
				}
			}
		}
		
		if(costMap.size() != n) {
			return -1;
		}
		
		var result = costMap.values().stream().max((a,b) -> a - b);
		return result.isPresent() ? result.get() : -1;
	}
	
	public static void main(String[] args) {
		// [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
		
		var result = new PracGeneric().networkDelayTime(new int[][] {{2,1,1},{2,3,1},{3,4,1}}, 4, 0);
		System.out.println(result);
	}
}
