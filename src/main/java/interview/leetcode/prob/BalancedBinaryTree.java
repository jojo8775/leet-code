package interview.leetcode.prob;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * @author jojo
 *
 */
public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
		dfs(root);
		return maxDiff < 2;
	}

	private int maxDiff;

	private int dfs(TreeNode node) {
		if (maxDiff > 1) {
			return maxDiff;
		}

		if (node == null) {
			return 0;
		}

		int left = dfs(node.left);
		int right = dfs(node.right);

		maxDiff = Math.max(Math.abs(left - right), maxDiff);

		return Math.max(left, right) + 1;
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
