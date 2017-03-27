package interview.leetcode.prob.paid;

/**
 * You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
 * @author jojo
 *Mar 27, 201712:25:03 AM
 */
public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        return createTree(s, new int[] { 0 }, s.length());
    }

    private TreeNode createTree(String s, int[] idx, int len) {
        if (idx[0] >= len) {
            return null;
        }

        int val = 0;
        int sign = 1;
        if (s.charAt(idx[0]) == '-') {
            idx[0]++;
            sign = -1;
        }

        while (idx[0] < len) {
            char ch = s.charAt(idx[0]);
            if (ch >= '0' && ch <= '9') {
                val *= 10;
                val += (int) (ch - '0');
                idx[0]++;
            } else {
                break;
            }
        }

        TreeNode node = new TreeNode(val * sign);

        if (idx[0] < len && s.charAt(idx[0]) == '(') {
            idx[0]++;
            node.left = createTree(s, idx, len);
        }

        if (idx[0] < len && s.charAt(idx[0]) == '(') {
            idx[0]++;
            node.right = createTree(s, idx, len);
        }

        if (idx[0] < len && s.charAt(idx[0]) == ')') {
            idx[0]++;
        }

        return node;
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
