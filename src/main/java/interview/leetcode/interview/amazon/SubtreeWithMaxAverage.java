package interview.leetcode.interview.amazon;

/**
 * Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
A subtree of a tree is the node which have at least 1 child plus all its descendants. The average value of a subtree is the sum of its values, divided by the number of nodes.

Example 1:

Input:
		 20
	   /   \
	 12     18
  /  |  \   / \
11   2   3 15  8

Output: 18
Explanation:
There are 3 nodes which have children in this tree:
12 => (11 + 2 + 3 + 12) / 4 = 7
18 => (18 + 15 + 8) / 3 = 13.67
20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125

18 has the maximum average so output 18.
 * @author jojo
 * May 17, 2020  1:24:59 AM
 */
public class SubtreeWithMaxAverage {
	public TreeNode findMaxAverage(TreeNode node) {
		NodePlaceholder[] result = new NodePlaceholder[1];
		find(node, result);

		return result != null && result[0].nodeCount > 1 ? result[0].node : null;
	}

	private NodePlaceholder find(TreeNode node, NodePlaceholder[] maxAvgNode) {
		if (node == null) {
			return null;
		}

		NodePlaceholder nodePlaceholder = new NodePlaceholder(node, 1, node.val);
		
		if(node.children == null) {
			return nodePlaceholder;
		}
		
		for (TreeNode child : node.children) {
			NodePlaceholder c = find(child, maxAvgNode);
			if (c != null) {
				nodePlaceholder.sum += c.sum;
				nodePlaceholder.nodeCount += c.nodeCount;
			}
		}

		if (nodePlaceholder.nodeCount > 1) {
			if (maxAvgNode[0] == null || (maxAvgNode[0].sum / maxAvgNode[0].nodeCount) < nodePlaceholder.sum
					/ nodePlaceholder.nodeCount) {
				maxAvgNode[0] = nodePlaceholder;
			}
		}

		return nodePlaceholder;
	}

	private static class NodePlaceholder {
		TreeNode node;
		int nodeCount, sum;

		public NodePlaceholder(TreeNode node, int nodeCount, int sum) {
			this.node = node;
			this.nodeCount = nodeCount;
			this.sum = sum;
		}
	}

	private static class TreeNode {
		int val;
		TreeNode[] children;

		public TreeNode(int val, TreeNode[] children) {
			this.val = val;
			this.children = children;
		}
	}

	public void findMaxAverage_execute() {
		TreeNode node = createTree();
		TreeNode result = findMaxAverage(node);
		System.out.println("max avg tree node: " + result.val);
	}
	
	private TreeNode createTree() {
		TreeNode n1 = new TreeNode(12,
				new TreeNode[] { new TreeNode(11, null), new TreeNode(2, null), new TreeNode(3, null) });
		TreeNode n2 = new TreeNode(18, new TreeNode[] {new TreeNode(15, null), new TreeNode(8, null)});
		
		return new TreeNode(20, new TreeNode[] {n1, n2});
	}

	public static void main(String[] args) {
		new SubtreeWithMaxAverage().findMaxAverage_execute();
	}
}
