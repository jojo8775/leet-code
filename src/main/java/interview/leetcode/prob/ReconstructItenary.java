package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * @author jojo
 *
 */
public class ReconstructItenary {
	public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> targets = new HashMap<String, PriorityQueue<String>>();

		for (String[] ticket : tickets) {
			targets.computeIfAbsent(ticket[0], k -> new PriorityQueue<String>()).add(ticket[1]);
		}

		List<String> route = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		stack.push("JFK");
		
		while (!stack.empty()) {
			while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()) {
				stack.push(targets.get(stack.peek()).poll());
			}
			
			route.add(0, stack.pop());
		}
		
		return route;
	}
}
