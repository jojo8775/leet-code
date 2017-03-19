package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
 * @author jojo
 *Mar 19, 20173:25:32 PM
 */
public class FindBottomLeftMostNode {
    public int findBottomLeftValue(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        TreeNode leftMostNode = null;

        // level order traversal
        while (!nodes.isEmpty()) {
            leftMostNode = nodes.get(0);
            List<TreeNode> nextRow = new ArrayList<TreeNode>();
            for (TreeNode node : nodes) {
                if (node.left != null) {
                    nextRow.add(node.left);
                }
                if (node.right != null) {
                    nextRow.add(node.right);
                }
            }

            nodes = nextRow;
        }

        return leftMostNode.val;
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
