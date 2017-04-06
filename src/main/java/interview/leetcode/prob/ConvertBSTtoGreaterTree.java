package interview.leetcode.prob;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13

 * @author jojo
 *Mar 25, 20179:22:43 AM
 */
public class ConvertBSTtoGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        reverseInOrder(root, new int[] { 0 });

        return root;
    }

    // reverse in-order
    private void reverseInOrder(TreeNode node, int[] sum) {
        if (node == null) {
            return;
        }

        reverseInOrder(node.right, sum);
        node.val += sum[0];
        sum[0] = node.val;
        reverseInOrder(node.left, sum);
    }

    private static   class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
