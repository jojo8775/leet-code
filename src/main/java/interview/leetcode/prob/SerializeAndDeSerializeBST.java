package interview.leetcode.prob;

import java.util.Stack;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary search tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 * 
 * @author jojo
 *
 */
public class SerializeAndDeSerializeBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            if (top != null) {
                sb.append(String.valueOf(top.val));
                // serializing tree using pre-order traversal
                stack.push(top.right);
                stack.push(top.left);
            } else {
                sb.append("#");
            }

            sb.append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] sArr = data.split(",");
        int[] idx = { 0 };

        return helper(idx, sArr);
    }

    private TreeNode helper(int[] idx, String[] sArr) {
        if (sArr[idx[0]].equals("#")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(sArr[idx[0]]));

        idx[0] += 1;
        node.left = helper(idx, sArr);
        idx[0] += 1;
        node.right = helper(idx, sArr);

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
