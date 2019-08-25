package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
Accepted
86,810
Submissions
145,896
 * @author jojo
 * Aug 25, 2019 1:52:22 AM
 */
public class AverageOfLevelInBinaryTree {
	public List<Double> averageOfLevels(TreeNode root) {
		List<TreeNode> list1 = new ArrayList<>();
		list1.add(root);

		List<Double> result = new ArrayList<>();

		while (!list1.isEmpty()) {
			long sum = 0;
			List<TreeNode> list2 = new ArrayList<>();
			for (int i = 0; i < list1.size(); i++) {
				TreeNode node = list1.get(i);
				sum += node.val;

				if (node.left != null) {
					list2.add(node.left);
				}

				if (node.right != null) {
					list2.add(node.right);
				}
			}

			result.add((1.0 * sum) / list1.size());
			list1 = list2;
		}

		return result;
	}

	private static class TreeNode {
		int val;
		TreeNode left, right;
	}
}
