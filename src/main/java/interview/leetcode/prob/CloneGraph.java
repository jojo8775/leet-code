package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 * @author jojo
 *
 */
public class CloneGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}

		Map<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		nodeMap.put(node, new UndirectedGraphNode(node.label));
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();

		Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
		stack.push(node);
		visited.add(node);

		while (!stack.isEmpty()) {
			UndirectedGraphNode top = stack.pop();

			UndirectedGraphNode topCopy = nodeMap.get(top);
			if (topCopy == null) {
				topCopy = new UndirectedGraphNode(top.label);
				nodeMap.put(top, topCopy);
			}

			for (UndirectedGraphNode n : top.neighbors) {
				if (!visited.contains(n)) {
					visited.add(n);
					stack.push(n);

					UndirectedGraphNode nCopy = nodeMap.get(n);
					if (nCopy == null) {
						nCopy = new UndirectedGraphNode(n.label);
						nodeMap.put(n, nCopy);
					}

					topCopy.neighbors.add(nCopy);
				} else {
					topCopy.neighbors.add(nodeMap.get(n));
				}
			}
		}

		return nodeMap.get(node);
	}

	private static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
}
