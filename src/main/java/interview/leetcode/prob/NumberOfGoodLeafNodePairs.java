package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.

 

Example 1:


Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
Example 2:


Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.
Example 3:

Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].
 

Constraints:

The number of nodes in the tree is in the range [1, 210].
1 <= Node.val <= 100
1 <= distance <= 10
Accepted
53,363
Submissions
82,435
 * 
 * Jul 17, 2024 - 10:21:00 PM
 * Jojo 
 */
public class NumberOfGoodLeafNodePairs {

	public int countPairs(TreeNode root, int distance) {
        int[] result = {0};
        findPairs(root, result, distance);

        // since each nodes will be counted twice.
        return result[0] / 2;
    }

    private List<Integer> findPairs(TreeNode node, int[] result, int dist){
        // if the node is null there is no child to explore.
        if(node == null){
            return new ArrayList<>();
        }

        // if the node is leaf there no depth to it.
        if(node.left == null && node.right == null){
            return Arrays.asList(1);
        }

        // finding the left node lenght
        List<Integer> left = findPairs(node.left, result, dist);
        
        // finding the right node lenght
        List<Integer> right = findPairs(node.right, result, dist);

        // collection of lenght which needs to be passed to parent.
        List<Integer> potentialNodes = new ArrayList<>();

        // traversing each left and right lenght pair to check if the distance is <= given dist
        for(int l : left){
            for(int r : right){
                if(l + r <= dist){
                    result[0] = result[0] + 2;
                }
            }
    
            // if the left dist + 1 (because of current node) is not less than given dist then no point in returning to parent.
            if(l + 1 < dist){
                potentialNodes.add(l + 1);
            }
        }

        // if the right dist + 1 (because of current node) is not less than given dist then no point in returning to parent.
        for(int r : right){
            if(r + 1 < dist){
                potentialNodes.add(r + 1);
            }
        }
    
        // System.out.println("n:" + node.val + "  r:" + result[0]);
        return potentialNodes;
    }
    
    public int countPairs_11(TreeNode root, int distance) {
        Map<TreeNode, TreeNode> map = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<TreeNode> leafNodes = new ArrayList<>();

        while(!queue.isEmpty()){
            TreeNode top = queue.poll();

            if(top.left != null){
                map.put(top.left, top);
                queue.offer(top.left);
            }

            if(top.right != null){
                map.put(top.right, top);
                queue.offer(top.right);
            }

            if(top.left == null && top.right == null){
                leafNodes.add(top);
            }
        }

        int result = 0;

        for(TreeNode leaf : leafNodes){
            result += getGoalLeaf(leaf, map, distance);
        }

        return result / 2;
    }
	
	private static class TreeNode{
		int val;
		TreeNode left, right;
	}
}
