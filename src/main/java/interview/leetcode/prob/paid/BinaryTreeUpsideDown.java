package interview.leetcode.prob.paid;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
 * @author jojo
 *
 */
public class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode node) {
		if (node == null) {
			return node;
		}

		flipTree(node);
		return root;
	}

	private TreeNode root = null;

	private TreeNode flipTree(TreeNode node) {
		if (node.left == null) {
			root = node;
			return node;
		}

		TreeNode curParent = flipTree(node.left);

		curParent.left = node.right;
		curParent.right = node;
		node.left = null;
		node.right = null;

		return node;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
