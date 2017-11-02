package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<TreeNode>();
        }

        return createTrees(1, n);
    }

    private List<TreeNode> createTrees(int beg, int end) {
        if (beg > end) {
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(null);
            return nodes;
        }

        List<TreeNode> result = new ArrayList<>();
        for (int i = beg; i <= end; i++) {
            List<TreeNode> leftNodes = createTrees(beg, i - 1);
            List<TreeNode> rightNodes = createTrees(i + 1, end);

            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;

                    result.add(node);
                }
            }
        }

        return result;
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
