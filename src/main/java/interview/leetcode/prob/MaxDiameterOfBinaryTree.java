package interview.leetcode.prob;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].


 * @author jojo
 *
 */
public class MaxDiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        // passing an array so that it can be modified by reference.
        int[] result = { 0 };

        maxDiameter(root, result);

        return result[0];
    }

    private int maxDiameter(TreeNode node, int[] result) {
        if (node == null) {
            return 0;
        }

        int left = maxDiameter(node.left, result);
        int right = maxDiameter(node.right, result);

        // max of prev diameter or current
        result[0] = Math.max(left + right, result[0]);

        // contribution of current node to its parent
        return Math.max(left, right) + 1;
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
