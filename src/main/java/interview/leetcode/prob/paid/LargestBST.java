package interview.leetcode.prob.paid;

public class LargestBST {

	public static void main(String[] args) {

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
