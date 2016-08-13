package interview.leetcode.prob;

/**
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia: In a complete binary
 * tree every level, except possibly the last, is completely filled, and all
 * nodes in the last level are as far left as possible. It can have between 1
 * and 2h nodes inclusive at the last level h.
 * 
 * @author jojo
 *
 */
public class CountCompleteTreeNodes {
	public int countNodes(TreeNode root) {
		int leftDepth = leftDepth(root);
		int rightDepth = rightDepth(root);

		if (leftDepth == rightDepth) {
			return (1 << leftDepth) - 1;
		} else {
			return 1 + countNodes(root.left) + countNodes(root.right);
		}
	}

	private int leftDepth(TreeNode node) {
		int i = 0;
		while (node != null) {
			i++;
			node = node.left;
		}

		return i;
	}

	private int rightDepth(TreeNode node) {
		int i = 0;
		while (node != null) {
			i++;
			node = node.right;
		}

		return i;
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
