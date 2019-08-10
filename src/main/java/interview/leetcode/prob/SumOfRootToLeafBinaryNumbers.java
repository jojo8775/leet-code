package interview.leetcode.prob;

/**
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

 

Example 1:



Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 

Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.

 * @author jojo
 * Jul 28, 2019 5:58:42 PM
 */
public class SumOfRootToLeafBinaryNumbers {
	public int sumRootToLeaf(TreeNode root) {
		return findPathVal(root, 0);
	}

	private int findPathVal(TreeNode node, int curSum) {
		if (node == null) {
			return 0;
		}

		int updatedSum = curSum * 2 + node.val;

		return node.left == null && node.right == null ? updatedSum
				: findPathVal(node.left, updatedSum) + findPathVal(node.right, updatedSum);
	}

	private static class TreeNode {
		TreeNode left, right;
		int val;
	}
}
