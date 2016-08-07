package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * @author jojo
 *
 */
public class UniqueBinaryTreeII {
	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new ArrayList<TreeNode>();
		}

		List<TreeNode> result = generateTrees(1, n);
		return result;
	}

	private List<TreeNode> generateTrees(int start, int end) {
		List<TreeNode> trees = new ArrayList<TreeNode>();

		if (start > end) {
			trees.add(null);
			return trees;
		}

		for (int i = start; i <= end; i++) {
			List<TreeNode> leftNodes = generateTrees(start, i - 1);
			List<TreeNode> rightNodes = generateTrees(i + 1, end);

			for (TreeNode lNode : leftNodes) {
				for (TreeNode rNode : rightNodes) {
					TreeNode node = new TreeNode(i);
					node.left = lNode;
					node.right = rNode;

					trees.add(node);
				}
			}
		}

		return trees;
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
		}
	}

	public static void main(String[] args) {
		new UniqueBinaryTreeII().generateTrees(3);
	}
}
