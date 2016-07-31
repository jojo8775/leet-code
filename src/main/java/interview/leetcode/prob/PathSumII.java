package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

 * @author jojo
 *
 */
public class PathSumII {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		dfs(result, root, new ArrayList<Integer>(), sum, 0);
		return result;
	}

	private void dfs(List<List<Integer>> result, TreeNode node, List<Integer> path, int sum, int val) {
		if (node == null) {
			return;
		}

		val = val + node.val;

		if (sum == val && node.left == null && node.right == null) {
			List<Integer> list = new ArrayList<Integer>(path);
			list.add(node.val);
			result.add(list);
			return;
		}

		path.add(node.val);
		dfs(result, node.left, path, sum, val);
		dfs(result, node.right, path, sum, val);
		path.remove(path.size() - 1);
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
