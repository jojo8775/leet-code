package interview.leetcode.interview.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriticalRouters {
	public List<Pair> getCriticalConnections(int nodes, int edges, List<Pair> connectors){
		Map<Integer, Set<Integer>> adjacent = new HashMap<>();
		
		for(Pair connector : connectors) {
			populateAdjacent(adjacent, connector.x, connector.y);
			populateAdjacent(adjacent, connector.y, connector.x);
		}
		
		List<Pair> criticalPaths = new ArrayList<>();
		
		for(Pair connector : connectors) {
			adjacent.get(connector.x).remove(connector.y);
			adjacent.get(connector.y).remove(connector.x);
			
			Set<Integer> visited = new HashSet<>();
			dfs(visited, adjacent, 1);
			
			if(visited.size() != nodes) {
				if(connector.x > connector.y) {
					criticalPaths.add(new Pair(connector.y, connector.x));
				}
				else {
					criticalPaths.add(connector);
				}
			}
			
			adjacent.get(connector.x).add(connector.y);
			adjacent.get(connector.y).add(connector.x);
		}
		
		return criticalPaths;
	}
	
	private void dfs(Set<Integer> visited, Map<Integer, Set<Integer>> adjacent, int node) {
		if(visited.add(node)) {
			for(Integer n : adjacent.get(node)) {
				dfs(visited, adjacent, n);
			}
		}
	}
	
	private Map<Integer, Set<Integer>> populateAdjacent(Map<Integer, Set<Integer>> adjacent, int x, int y){
		Set<Integer> edges = adjacent.get(x);
		if(edges == null) {
			edges = new HashSet<>();
			adjacent.put(x, edges);
		}
		
		edges.add(y);
		
		return adjacent;
	}
	
	private static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		print(new CriticalRouters().getCriticalConnections(7, 7, Arrays.asList(new Pair(0,1), new Pair(0,2), new Pair(1,3), new Pair(2,3), new Pair(2,5), new Pair(5,6), new Pair(3,4))));
	}
	
	private static void print(List<Pair> criticalPaths) {
		criticalPaths.stream().forEach(x -> System.out.println(" [" + x.x + " - " + x.y + "] "));
	}
}
