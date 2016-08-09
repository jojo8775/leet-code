package interview.leetcode.prob;

/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 * @author jojo
 *
 */
public class MinimumDepthOfBinaryTree {
	private int minDepth = Integer.MAX_VALUE;

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		dfs(root, 1);
		return minDepth;
	}

	private void dfs(TreeNode node, int depth) {
		if (node == null) {
			return;
		}

		if (depth > minDepth) {
			return;
		}

		if (node.left == null && node.right == null) {
			minDepth = Math.min(minDepth, depth);
			return;
		}

		dfs(node.left, depth + 1);
		dfs(node.right, depth + 1);
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
