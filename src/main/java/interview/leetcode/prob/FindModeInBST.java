package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 * @author jojo
 *Mar 19, 20172:09:08 AM
 */
public class FindModeInBST {
    public int[] findMode(TreeNode root) {
        // [0] - prev val, [1] = current count, [2] max count
        int[] placeHolder = new int[3];

        // inorder since this is a a BST
        // running first time to find the max ocurrence of a node
        inorder(root, placeHolder, null);

        List<Integer> resultList = new ArrayList<Integer>();

        // reseting the current count and prev val
        placeHolder[0] = 0;
        placeHolder[1] = 0;

        // running second time to get all the repeating node with same occurence
        // frequency
        inorder(root, placeHolder, resultList);

        // storing the value in an array from list.
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    private void inorder(TreeNode node, int[] placeHolder, List<Integer> resultList) {
        if (node == null) {
            return;
        }

        // traversing left node first
        inorder(node.left, placeHolder, resultList);

        // if prev value is same as current value increase current count by 1
        if (placeHolder[0] == node.val) {
            placeHolder[1]++;
        }
        // else set prev value to currnt and make the currenct count = 1
        else {
            placeHolder[1] = 1;
            placeHolder[0] = node.val;
        }

        // if current count is greater than max frequency then update max
        // frequency to current count
        if (placeHolder[1] > placeHolder[2]) {
            placeHolder[2] = placeHolder[1];
        }

        // if the input list is not null and current count is same as max
        // frequency then record current node
        if (placeHolder[2] == placeHolder[1] && resultList != null) {
            resultList.add(node.val);
        }

        // traversing right node
        inorder(node.right, placeHolder, resultList);
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
