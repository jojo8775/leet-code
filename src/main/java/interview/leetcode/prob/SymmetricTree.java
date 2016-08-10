package interview.leetcode.prob;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
 * @author jojo
 *
 */
public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		return symetric(root.left, root.right);
	}

	private boolean symetric(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null || node2 == null || node1.val != node2.val) {
			return false;
		}

		return symetric(node1.left, node2.right) && symetric(node1.right, node2.left);
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
