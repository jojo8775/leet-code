package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 * @author jojo
 *
 */
public class BinaryTreeLevelOrderTraversalII {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		List<TreeNode> nodes = new ArrayList<TreeNode>();
		nodes.add(root);
		while (true) {
			List<TreeNode> curNodes = new ArrayList<TreeNode>();
			List<Integer> values = new ArrayList<Integer>();
			for (TreeNode node : nodes) {
				values.add(node.val);
				if (node.left != null) {
					curNodes.add(node.left);
				}

				if (node.right != null) {
					curNodes.add(node.right);
				}
			}

			result.add(0, values);
			nodes = curNodes;

			if (curNodes.isEmpty()) {
				break;
			}
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
