package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *May 15, 201712:54:58 AM
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(s);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isSame(node, t)) {
                return true;
            }

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return false;
    }

    private boolean isSame(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 == null || n2 == null || n1.val != n2.val) {
            return false;
        }

        return isSame(n1.left, n2.left) && isSame(n1.right, n2.right);
    }

    private static class TreeNode {
        TreeNode left = null, right = null;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
