package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
 * @author jojo
 *
 */
public class HouseRobberIII {
	private Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();

	public int rob(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (map.containsKey(root)) {
			return map.get(root);
		}

		int val = 0;
		if (root.left != null) {
			val = rob(root.left.right) + rob(root.left.left);
		}

		if (root.right != null) {
			val += rob(root.right.right) + rob(root.right.left);
		}

		int res = Math.max(val + root.val, rob(root.left) + rob(root.right));
		map.put(root, res);

		return res;
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
