package interview.leetcode.practice.round3.tree;

import java.util.HashMap;
import java.util.Map;

public class MaxNodeSumFrequency {

    public int[] maxCount(TreeNode node) {
        int[] placeHolder = new int[2];
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        postOrder(node, sumCountMap, placeHolder);

        int[] result = new int[placeHolder[1]];
        int idx = 0;

        for (Map.Entry<Integer, Integer> entry : sumCountMap.entrySet()) {
            if (entry.getValue() == placeHolder[0]) {
                result[idx++] = entry.getKey();
            }
        }

        return result;
    }

    private int postOrder(TreeNode node, Map<Integer, Integer> sumCountMap, int[] placeHolder) {
        if (node == null) {
            return 0;
        }

        int sum = postOrder(node.left, sumCountMap, placeHolder);
        sum += postOrder(node.right, sumCountMap, placeHolder);
        sum += node.val;

        sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);

        if (placeHolder[0] < sumCountMap.get(sum)) {
            placeHolder[0] = sumCountMap.get(sum);
            placeHolder[1] = 0;
        }

        if (placeHolder[0] == sumCountMap.get(sum)) {
            placeHolder[1]++;
        }
        
        return sum;
    }

    private static class TreeNode {
        TreeNode left = null, right = null;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
