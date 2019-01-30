package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \    
    1   3  

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
Note:

The size of the BST will be between 2 and 100.
The BST is always valid, each node's value is an integer, and each node's value is different.
Accepted
24,095
Submissions
48,726
 * @author jojo
 * Jan 16, 2019 9:50:10 PM
 */
public class MinimumDistanceBetweenBSTNodes {
	public int minDiffInBST(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		Integer prev = null, result = Integer.MAX_VALUE;
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				if (prev != null) {
					result = Math.min(result, root.val - prev);
				}

				prev = root.val;
				root = root.right;
			}
		}

		return result;
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
