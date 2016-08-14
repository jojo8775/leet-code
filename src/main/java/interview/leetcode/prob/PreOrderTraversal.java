package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
 * @author jojo
 *
 */
public class PreOrderTraversal {
	public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<Integer>();
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        List<Integer> result = new ArrayList<Integer>();
        
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();
            result.add(top.val);
            
            if(top.right != null){
                stack.push(top.right);
            }
            
            if(top.left != null){
                stack.push(top.left);
            }
        }
        
        return result;
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
