package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
 * @author jojo
 *
 */
public class BinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
		if (root == null) {
			return new ArrayList<Integer>();
		}

		List<Integer> result = new ArrayList<Integer>();

		List<TreeNode> list1 = new ArrayList<TreeNode>();
		list1.add(root);

		while (true) {
			result.add(list1.get(0).val);

			List<TreeNode> list2 = new ArrayList<TreeNode>();
			for (TreeNode node : list1) {
				if (node.right != null) {
					list2.add(node.right);
				}

				if (node.left != null) {
					list2.add(node.left);
				}
			}

			list1 = list2;
			if (list1.isEmpty()) {
				break;
			}
		}

		return result;
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
