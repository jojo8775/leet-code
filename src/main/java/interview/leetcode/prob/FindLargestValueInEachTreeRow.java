package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
Subscribe to see which companies asked this question.
 * @author jojo Mar 19, 201711:55:23 PM
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        if (root == null) {
            return result;
        }

        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int max = Integer.MIN_VALUE;
            List<TreeNode> nextRow = new ArrayList<TreeNode>();
            for (TreeNode node : nodes) {
                max = Math.max(max, node.val);

                if (node.left != null) {
                    nextRow.add(node.left);
                }

                if (node.right != null) {
                    nextRow.add(node.right);
                }
            }

            result.add(max);
            nodes = nextRow;
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
