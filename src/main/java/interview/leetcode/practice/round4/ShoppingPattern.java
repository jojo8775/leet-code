package interview.leetcode.practice.round4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShoppingPattern {
	public static int getMinScore(int productNodes, List<Integer> productsForm, List<Integer> productsTo) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		
		// populate the graph as the displayed table.
		for(int i=0; i<productsForm.size(); i++) {
			if(!graph.containsKey(productsTo.get(i))) {
				graph.put(productsTo.get(i), new HashSet<Integer>());
			}
			
			graph.get(productsTo.get(i)).add(productsForm.get(i));
			
			if(!graph.containsKey(productsForm.get(i))) {
				graph.put(productsForm.get(i), new HashSet<Integer>());
			}
			
			graph.get(productsForm.get(i)).add(productsTo.get(i));
		}
		
		int result = Integer.MAX_VALUE;
		
		// do dfs 
		for(int i=1; i<=productNodes; i++) {
			Set<Integer> l1 = graph.get(i);
			if(l1 == null || l1.size() < 2) {
				continue;
			}
			
			
		}
		
		return -1;
	}
}
