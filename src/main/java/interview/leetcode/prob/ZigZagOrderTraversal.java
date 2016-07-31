package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

 * @author jojo
 *
 */
public class ZigZagOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>(), currentNodes = null;
		nodes.add(root);
		boolean leftToRight = false;

		while (!nodes.isEmpty()) {
			List<Integer> rowNodes = new ArrayList<Integer>(nodes.size());
			currentNodes = new LinkedList<TreeNode>();
			while (!nodes.isEmpty()) {
				TreeNode node = nodes.removeLast();
				rowNodes.add(node.val);
				if (leftToRight) {
					if (node.right != null) {
						currentNodes.add(node.right);
					}

					if (node.left != null) {
						currentNodes.add(node.left);
					}
				} else {
					if (node.left != null) {
						currentNodes.add(node.left);
					}

					if (node.right != null) {
						currentNodes.add(node.right);
					}
				}
			}

			result.add(rowNodes);

			nodes = currentNodes;
			leftToRight = !leftToRight;
		}

		return result;
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
