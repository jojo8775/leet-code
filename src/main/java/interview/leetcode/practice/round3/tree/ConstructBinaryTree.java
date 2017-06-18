package interview.leetcode.practice.round3.tree;

public class ConstructBinaryTree {
    public TreeNode str2tree(String s) {
        return create(s, new int[] { 0 });
    }

    private TreeNode create(String str, int[] idx) {
        if (idx[0] == str.length()) {
            return null;
        }

        int val = 0, sign = 1;
        TreeNode node = null;
        while (idx[0] < str.length()) {
            char ch = str.charAt(idx[0]++);

            if (ch >= '0' && ch <= '9') {
                val *= 10;
                val += (int) (ch - '0');
            } else if (ch == '(') {
                if (node == null) {
                    node = new TreeNode(val * sign);
                }

                if (node.left == null) {
                    node.left = create(str, idx);
                } else if (node.right == null) {
                    node.right = create(str, idx);
                }
            } else if (ch == ')') {
                return node;
            } else {
                if (ch == '-') {
                    sign *= -1;
                }
            }

        }

        return node;
    }

    private static class TreeNode {
        TreeNode left = null, right = null;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args){
        TreeNode node = new ConstructBinaryTree().str2tree("4(2(3))");
    }
}
