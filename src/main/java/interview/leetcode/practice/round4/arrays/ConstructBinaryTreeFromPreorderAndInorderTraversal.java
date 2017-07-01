package interview.leetcode.practice.round4.arrays;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return createTreeNode(preorder, inorder, new int[] { 0 }, 0, inorder.length - 1);
    }

    private TreeNode createTreeNode(int[] preorder, int[] inorder, int[] idx, int beg, int end) {
        if (idx[0] >= preorder.length) {
            return null;
        }

        int index = findIndex(inorder, beg, end, preorder[idx[0]]);

        if (index == -1) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[idx[0]]);
        idx[0]++;
        node.left = createTreeNode(preorder, inorder, idx, beg, index - 1);
        node.right = createTreeNode(preorder, inorder, idx, index + 1, end);

        return node;
    }

    private int findIndex(int[] inorder, int beg, int end, int target) {
        for (int i = beg; i <= end; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }

        return -1;
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
