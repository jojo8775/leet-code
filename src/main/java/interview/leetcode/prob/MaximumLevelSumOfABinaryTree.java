package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 

Example 1:



Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 

Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5
 * @author jojo
 * Nov 15, 2019 12:22:30 AM
 */
public class MaximumLevelSumOfABinaryTree {
    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE, level = -1;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int curLevel = 0;
        while(!queue.isEmpty()){
            curLevel++;
            int size = queue.size(), temp = 0;
            
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                temp += node.val;
                
                if(node.left != null){
                    queue.offer(node.left);
                }
                
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            
            if(max < temp){
                level = curLevel;
                max = temp;
            }
        }
        
        return level;
    }
	
	private static class TreeNode{
		int val;
		TreeNode left, right;
	}
}
