package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
 * @author jojo
 *
 */
public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> paths = new ArrayList<String>();
		if (root == null) {
			return paths;
		}

		dfs(paths, root, new ArrayList<String>());
		return paths;
	}

	private void dfs(List<String> paths, TreeNode node, List<String> path) {
		if (node.left == null && node.right == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < path.size(); i++) {
				sb.append(path.get(i)).append("->");
			}

			sb.append(String.valueOf(node.val));

			paths.add(sb.toString());
			return;
		}

		path.add(String.valueOf(node.val));
		if (node.left != null) {
			dfs(paths, node.left, path);
		}

		if (node.right != null) {
			dfs(paths, node.right, path);
		}
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
