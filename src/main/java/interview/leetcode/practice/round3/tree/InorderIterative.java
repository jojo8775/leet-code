package interview.leetcode.practice.round3.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderIterative {
    public List<Integer> traverse(TreeNode node) {
        if (node == null) {
            return new ArrayList<Integer>();
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }

        return result;
    }

    private static class TreeNode {
        TreeNode left = null, right = null;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode createBST(int[] arr) {
        return createBST(arr, 0, arr.length - 1);
    }

    private TreeNode createBST(int[] arr, int beg, int end) {
        if (beg > end) {
            return null;
        }

        int mid = beg + (end - beg) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = createBST(arr, beg, mid - 1);
        node.right = createBST(arr, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        InorderIterative io = new InorderIterative();
        TreeNode root = io.createBST(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

        List<Integer> result = io.traverse(root);
        for (int n : result) {
            System.out.print(n + ", ");
        }
    }
}
