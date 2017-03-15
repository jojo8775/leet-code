package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * @author jojo
 *
 */
public class MinimumAbsoluteDifference {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> nodeValues = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        // inorder traversal as this is a BST
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                nodeValues.add(root.val);
                root = root.right;
            }
        }

        if (nodeValues.size() < 2) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < nodeValues.size(); i++) {
            result = Math.min(result, Math.abs(nodeValues.get(i - 1) - nodeValues.get(i)));
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
