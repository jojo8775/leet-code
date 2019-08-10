package interview.leetcode.prob.paid;

/**
 * Given a non-empty binary search tree and a target value, find the value in
 * the BST that is closest to the target.
 * 
 * Note: Given target value is a floating point. You are guaranteed to have only
 * one unique value in the BST that is closest to the target.
 * 
 * @author jojo
 *
 */
public class ClosestBinarySearchTreeValue {

	public int closestValue_Iterative(TreeNode root, double target) {
		int result = root.val;
		
		while (root != null) {
			// update closestVal if the current value is closer to target
			result = (Math.abs(target - root.val) < Math.abs(target - result)) ? root.val : result;
			if (result == target) { // already find the best result
				return result;
			}
			root = (root.val > target) ? root.left : root.right; // binary search
		}
		return result;
	}

	public int closestValue(TreeNode root, double target) {
		return getClosestValue(root, target).val;
	}

	private TreeNode getClosestValue(TreeNode node, double target) {
		if (node == null) {
			return null;
		}

		TreeNode childNode = null;

		if (node.val > target) {
			childNode = getClosestValue(node.left, target);
		} else if (node.val < target) {
			childNode = getClosestValue(node.right, target);
		}

		if (childNode == null) {
			return node;
		}

		double diff1 = Math.abs(target - (double) node.val);
		double diff2 = Math.abs(target - (double) childNode.val);

		if (diff1 > diff2) {
			return childNode;
		}

		return node;
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
