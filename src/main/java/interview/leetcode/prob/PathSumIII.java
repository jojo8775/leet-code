package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 * @author jojo
 *
 */
public class PathSumIII {
	public int pathSum(TreeNode root, int sum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L,1);
        
        return getCount(root, (long)sum, 0L, map);
    }
    
    private int getCount(TreeNode node, long target, long sumSofar, Map<Long, Integer> map){
        if(node == null){
            return 0;
        }
        
        sumSofar += (long)node.val;
        
        int count = map.getOrDefault(sumSofar - target, 0);
        
        map.put(sumSofar, map.getOrDefault(sumSofar, 0) + 1);
        count += getCount(node.left, target, sumSofar, map);
        count += getCount(node.right, target, sumSofar, map);
        map.put(sumSofar, map.getOrDefault(sumSofar, 0) - 1);
        
        return count;
    }
	
	/*
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, 1);

        return getCount(root, sumMap, sum, 0);
    }

    private int getCount(TreeNode node, Map<Integer, Integer> sumMap, int target, int sumSofar) {
        if (node == null) {
            return 0;
        }

        // sum so far is used to get the cumulative sum of node vals in a path.
        sumSofar += node.val;

        // in a path if sum so far - target exists then there is a sub path
        // which forms the target
        int count = sumMap.getOrDefault(sumSofar - target, 0);

        // sum so far is indexed with count so that its easy to find
        sumMap.put(sumSofar, sumMap.getOrDefault(sumSofar, 0) + 1);

        // exploring the left child
        count += getCount(node.left, sumMap, target, sumSofar);

        // exploring the right child
        count += getCount(node.right, sumMap, target, sumSofar);

        // removing the contribution of this current node from the sum
        sumMap.put(sumSofar, sumMap.get(sumSofar) - 1);

        return count;
    }
	*/
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
