package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
 

Example 2:

Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
 


 * @author jojo
 * Aug 25, 2019 3:00:16 PM
 */
public class TwoSumIV {
	public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        
        return inorder(root, k, set);
    }
    
    private boolean inorder(TreeNode node, int target, Set<Integer> set){
        if(node == null){
            return false;
        }
        
        if(set.contains(target - node.val)){
            return true;
        }
        
        set.add(node.val);
        
        return inorder(node.left, target, set) || inorder(node.right, target, set);
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
