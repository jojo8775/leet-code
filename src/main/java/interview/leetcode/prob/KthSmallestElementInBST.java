package interview.leetcode.prob;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).

 * @author jojo
 *
 */
public class KthSmallestElementInBST {
	private int k, val;

	public int kthSmallest(TreeNode root, int k) {
		this.k = k;
		inorder(root);
		return val;
	}

	private void inorder(TreeNode node) {
		if (node == null) {
			return;
		}

		inorder(node.left);
		if (--k == 0) {
			val = node.val;
			return;
		}
		inorder(node.right);
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
