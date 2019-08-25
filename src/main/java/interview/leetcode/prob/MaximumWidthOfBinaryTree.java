package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
 * @author jojo
 * Aug 25, 2019 4:20:11 PM
 */
public class MaximumWidthOfBinaryTree {
	public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        // preserving min and max at each level
        Map<Integer, int[]> map = new HashMap<>();
        find(root, map, 0, 0);
        
        int result = 1;
        
        for(int[] val : map.values()){
            result = Math.max(result, val[1] - val[0] + 1);
        }
        
        return result;
    }
    
    private void find(TreeNode node, Map<Integer, int[]> map, int level, int pos){
        if(node == null){
            return;
        }
        
        int[] val = map.get(level);
        if(val == null){
            val = new int[2];
            val[0] = Integer.MAX_VALUE;
            val[1] = Integer.MIN_VALUE;
        }
        
        val[0] = Math.min(val[0], pos);
        val[1] = Math.max(val[1], pos);
        
        map.put(level, val);
        
        find(node.left, map, level + 1, pos * 2);
        find(node.right, map, level + 1, pos * 2 + 1);
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
