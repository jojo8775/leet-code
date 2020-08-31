package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
Accepted
40,968
Submissions
83,592
 * @author jojo
 * Aug 30, 2020  4:37:31 PM
 */
public class FindEventualSafeStates {
	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> result = new ArrayList<>();

		// if the input is empty return empty result
		if (graph == null || graph.length == 0) {
			return result;
		}

		int[] eventualSafePoints = new int[graph.length];

		for (int startPoint = 0; startPoint < graph.length; startPoint++) {
			if (dfs(graph, startPoint, eventualSafePoints)) {
				result.add(startPoint);
			}
		}

		return result;
	}

	private boolean dfs(int[][] graph, int startPoint, int[] eventualSafePoints) {
		if (eventualSafePoints[startPoint] != 0) {
			return eventualSafePoints[startPoint] == 1;
		}

		eventualSafePoints[startPoint] = 2; // marking it false

		for (int nei : graph[startPoint]) {
			if (!dfs(graph, nei, eventualSafePoints)) {
				return false;
			}
		}

		eventualSafePoints[startPoint] = 1; // marking it true
		return true;
	}
}
