package interview.leetcode.prob;

import java.util.Stack;

/**
 * Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 * @author jojo
 *
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;

        if (root == null) {
            return sum;
        }

        Stack<Node> stack = new Stack<Node>();
        stack.push(new Node(root, false));

        while (!stack.isEmpty()) {
            Node top = stack.pop();
            TreeNode node = top.node;

            // if its a leaf node
            if (node.left == null && node.right == null && top.leftNode) {
                sum += node.val;
            } else {
                if (node.left != null) {
                    stack.push(new Node(node.left, true));
                }

                if (node.right != null) {
                    stack.push(new Node(node.right, false));
                }
            }
        }

        return sum;
    }

    private static class Node {
        TreeNode node;
        boolean leftNode;

        public Node(TreeNode node, boolean leftNode) {
            this.node = node;
            this.leftNode = leftNode;
        }
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
