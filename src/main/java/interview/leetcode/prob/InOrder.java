package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class InOrder {
	public List<Integer> inorderTraversal(TreeNode root) {
		 if(root == null){
	            return new ArrayList<Integer>();
	        }
	        
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        stack.push(root);
	        Set<TreeNode> visited = new HashSet<TreeNode>();
	        
	        List<Integer> result = new ArrayList<Integer>();
	        while(!stack.isEmpty()){
	            TreeNode node = stack.peek();
	            while(node.left != null && !visited.contains(node.left)){
	                stack.push(node.left);
	                visited.add(node.left);
	                node = node.left;
	            }
	            
	            node = stack.pop();
	            result.add(node.val);
	            
	            if(node.right != null){
	                stack.add(node.right);
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
