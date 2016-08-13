package interview.leetcode.prob;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you
 * devise a constant space solution?
 * 
 * @author jojo
 *
 */
public class RecoverBinarSearchTree {
	private TreeNode t1 = null, t2 = null, prev = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		find(root);

		// time to swap
		int temp = t1.val;
		t1.val = t2.val;
		t2.val = temp;
	}

	private void find(TreeNode root) {
		if (root == null) {
			return;
		}

		find(root.left);

		if (t1 == null && prev.val >= root.val) {
			t1 = prev;
		}

		if (t1 != null && prev.val >= root.val) {
			t2 = root;
		}

		prev = root;

		find(root.right);
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
