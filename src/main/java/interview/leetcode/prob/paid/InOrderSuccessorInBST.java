package interview.leetcode.prob.paid;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a binary search tree and a node in it, find the in-order successor of
 * that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null.
 * 
 * @author jojo
 *
 */
public class InOrderSuccessorInBST {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		// if p is not the right most node
		if (p.right != null) {
			TreeNode node = p.right;
			while (node.left != null) {
				node = node.left;
			}

			return node;
		}

		// performing in-order traversal interatively
		Set<TreeNode> visited = new HashSet<TreeNode>();
		visited.add(null);

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		boolean nodeFound = false;

		while (!stack.isEmpty()) {
			while (!stack.isEmpty()) {
				if (visited.contains(stack.peek().left)) {
					TreeNode top = stack.pop();

					// if p found then top is the successor
					if (nodeFound) {
						return top;
					}

					if (top.equals(p)) {
						nodeFound = true;
					}

					if (!visited.contains(top.right)) {
						stack.push(top.right);
						visited.add(top.right);
					}
				} else {
					TreeNode top = stack.peek();
					stack.push(top.left);
					visited.add(top.left);
				}
			}
		}

		// p is the last entry
		return null;
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
