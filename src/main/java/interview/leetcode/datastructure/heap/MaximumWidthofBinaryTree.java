package interview.leetcode.datastructure.heap;

import java.util.LinkedList;
import java.util.Queue;

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
 *Feb 14, 201812:45:41 PM
 */
public class MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        int result = 0;
        if(root == null){
            return result;
        }
        
        Queue<Integer> posQueue = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        
        nodeQueue.offer(root);
        posQueue.offer(1);
        
        while(!nodeQueue.isEmpty()){
            int size = nodeQueue.size(), left = 0, right = 0;
            
            for(int i=0; i<size; i++){
                TreeNode cur = nodeQueue.poll();
                int pos = posQueue.poll();
                if(cur.left != null){
                    nodeQueue.offer(cur.left);
                    posQueue.offer(pos*2);
                }
                
                if(cur.right != null){
                    nodeQueue.offer(cur.right);
                    posQueue.offer(pos*2 + 1);
                }
                
                if(i == 0){
                    left = pos;
                }
                
                if(i == size - 1){
                    right = pos;
                }
            }
            
            result = Math.max(result, right - left + 1);
        }
        
        return result;
    }
    
    private static class TreeNode{
        int val;
        TreeNode left = null, right = null;
        public TreeNode(int val){
            this.val = val;
        }
    }
}
