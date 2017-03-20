package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * 
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 * @author jojo
 *
 */
public class MostFrequentSubtreeSum {
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> countBySumMap = new HashMap<Integer, Integer>();

        // [0] represents max frequency of a sum
        // [1] represents occurance of max frequency
        int[] placeHolder = { 0, 0 };

        postOrder(root, countBySumMap, placeHolder);

        int[] result = new int[placeHolder[1]];
        int idx = 0;

        for (Map.Entry<Integer, Integer> entry : countBySumMap.entrySet()) {
            if (entry.getValue() == placeHolder[0]) {
                result[idx++] = entry.getKey();
            }
        }

        return result;
    }

    public int postOrder(TreeNode node, Map<Integer, Integer> countBySumMap, int[] placeHolder) {
        if (node == null) {
            return 0;
        }

        int left = postOrder(node.left, countBySumMap, placeHolder);
        int right = postOrder(node.right, countBySumMap, placeHolder);

        int sum = left + right + node.val;
        countBySumMap.put(sum, countBySumMap.getOrDefault(sum, 0) + 1);

        if (countBySumMap.get(sum) > placeHolder[0]) {
            placeHolder[0] = countBySumMap.get(sum);
            placeHolder[1] = 0;
        }

        if (countBySumMap.get(sum) == placeHolder[0]) {
            placeHolder[1]++;
        }

        return sum;
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
