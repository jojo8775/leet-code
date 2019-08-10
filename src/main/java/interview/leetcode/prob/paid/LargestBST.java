package interview.leetcode.prob.paid;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 * @author jojo
 * Jul 20, 2019 12:59:14 PM
 */
public class LargestBST {

	public static void main(String[] args) {

	}

	public int largestBSTSubtree_1(TreeNode root) {
		int[] result = find(root);

		return result[2];
	}

	private int[] find(TreeNode node) {
		if (node == null) {
			return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
		}

		int[] left = find(node.left);
		int[] right = find(node.right);

		if (node.val > left[1] && node.val < right[0]) {
			return new int[] { Math.min(left[0], node.val), Math.max(right[1], node.val), left[2] + right[2] + 1 };
		}

		return new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2]) };
	}

	public int largestBSTSubtree(TreeNode node) {
		return findSubtree(node)[0];
	}

	private int[] findSubtree(TreeNode node) {
		if (node == null) {
			return new int[] { 0, 1 };
		}

		int[] leftChild = findSubtree(node.left);
		int[] rightChild = findSubtree(node.right);

		int leftChildVal = node.left == null ? Integer.MIN_VALUE : node.left.val;
		int rightChildVal = node.right == null ? Integer.MAX_VALUE : node.right.val;

		boolean isBinaryNode = leftChildVal <= node.val && rightChildVal > node.val;
		isBinaryNode = leftChild[1] == 1 && rightChild[1] == 1 && isBinaryNode;

		if (isBinaryNode) {
			return new int[] { leftChild[0] + rightChild[0] + 1, 1 };
		}

		return new int[] { Math.max(leftChild[0], rightChild[0]), 0 };
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
