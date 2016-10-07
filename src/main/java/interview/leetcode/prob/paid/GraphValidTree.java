package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * For example:
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return
 * false.
 * 
 * Show Hint
 * 
 * @author jojo
 *
 */
public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		// number of node should be 1 greater than edge
		if (edges.length + 1 != n) {
			return false;
		}

		boolean[] nodes = new boolean[n];
		int count = 0;

		for (int[] edge : edges) {
			// represents an egde whihc is already visited
			if (nodes[edge[0]] && nodes[edge[1]]) {
				count -= 2;
			}

			// represents a new dijoint edge is being visited
			else if (!nodes[edge[0]] && !nodes[edge[1]]) {
				count += 1;
			}

			// count will be less than 0 if there is a cycle.
			if (count < 0) {
				break;
			}

			// marking the node a visited
			nodes[edge[0]] = true;
			nodes[edge[1]] = true;
		}

		// count will be greater than one 1 if there is a disjoint edge
		return count == 0 || count == 1;
	}

	public boolean validTree_EasyToUnderstand(int n, int[][] edges) {
		boolean[] visitedNodes = new boolean[n];
		Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();

		for (int[] edge : edges) {
			graph.computeIfAbsent(edge[0], v -> new ArrayList<Integer>());
			graph.get(edge[0]).add(edge[1]);

			graph.computeIfAbsent(edge[1], v -> new ArrayList<Integer>());
			graph.get(edge[1]).add(edge[0]);
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);

		while (!queue.isEmpty()) {
			int top = queue.poll();

			// if a node is already visited then there is a cycle
			if (visitedNodes[top]) {
				return false;
			}

			visitedNodes[top] = true;

			// checks if a node is missing
			if (!graph.containsKey(top)) {
				continue;
			}

			// if there is a cycle then same node will be entered twice in the
			// queue
			for (int node : graph.get(top)) {
				if (!visitedNodes[node]) {
					queue.offer(node);
				}
			}
		}

		// checking for disjoint edge
		for (int i = 0; i < visitedNodes.length; i++) {
			if (!visitedNodes[i]) {
				return false;
			}
		}

		return true;
	}
}
