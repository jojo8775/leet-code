package interview.leetcode.practice.round3.tree;

import java.util.Stack;

public class KClosestBSTNode {

    public int[] findKNodes(TreeNode node, double target, int k) {
        Stack<TreeNode> successor = loadSuccessor(node, target);
        Stack<TreeNode> predecessasor = loadPredecessasor(node, target);

        if (!successor.isEmpty() && !predecessasor.isEmpty() && successor.peek() == predecessasor.peek()) {
            getSuccessasor(successor);
        }

        int[] result = new int[k];
        int idx = 0;
        while (k-- > 0) {
            if (successor.isEmpty()) {
                result[idx++] = getPredecessasor(predecessasor);
            } else if (predecessasor.isEmpty()) {
                result[idx++] = getSuccessasor(successor);
            } else {
                double diff1 = Math.abs(target - (successor.peek().val * 1.0));
                double diff2 = Math.abs(target - (predecessasor.peek().val * 1.0));

                if (diff1 < diff2) {
                    result[idx++] = getSuccessasor(successor);
                } else {
                    result[idx++] = getPredecessasor(predecessasor);
                }
            }
        }

        return result;
    }

    private Stack<TreeNode> loadSuccessor(TreeNode node, double target) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node != null) {
            if (node.val == target) {
                stack.push(node);
                break;
            } else if (node.val < target) {
                node = node.right;
            } else {
                stack.push(node);
                node = node.left;
            }
        }

        return stack;
    }

    private int getSuccessasor(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        int val = top.val;
        top = top.right;
        while (top != null) {
            stack.push(top);
            top = top.left;
        }

        return val;
    }

    private Stack<TreeNode> loadPredecessasor(TreeNode node, double target) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node != null) {
            if (node.val == target) {
                stack.push(node);
                break;
            } else if (node.val > target) {
                node = node.left;
            } else {
                stack.push(node);
                node = node.right;
            }
        }

        return stack;
    }

    private int getPredecessasor(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        int val = top.val;
        top = top.left;
        while (top != null) {
            stack.push(top);
            top = top.right;
        }

        return val;
    }

    private static class TreeNode {
        TreeNode left = null, right = null;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
