package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.
 * @author jojo
 *
 */
public class ClosestBinarySearchTreeValueII {
	 public List<Integer> closestKValues(TreeNode root, double target, int k) {
	        Stack<TreeNode> predessesor = loadPredessesor(root, target);
	        Stack<TreeNode> successor = loadSuccessor(root, target);
	        
	        //if there is a node equal to target then it will be common for both stack. So need to remove one
	        if(!predessesor.isEmpty() && !successor.isEmpty() && successor.peek().val == predessesor.peek().val){
	            //dropping top entry from successor
	            getNextSuccessor(successor);
	        }
	        
	        List<Integer> result = new ArrayList<Integer>();
	        while(k-- > 0){
	            if(predessesor.isEmpty()){
	                result.add(getNextSuccessor(successor));
	            }
	            else if(successor.isEmpty()){
	                result.add(getNextPredessesor(predessesor));
	            }
	            else{
	                double successorDiff = Math.abs(target - (double) successor.peek().val);
	                double predessesorDiff = Math.abs(target - (double) predessesor.peek().val);
	                
	                if(successorDiff > predessesorDiff){
	                    result.add(getNextPredessesor(predessesor));
	                }
	                else{
	                    result.add(getNextSuccessor(successor));
	                }
	            }
	        }
	        
	        return result;
	    }
	    
	    // gets a stack of all the node from root which are closest greater than target
	    private Stack loadSuccessor(TreeNode root, double target){
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        TreeNode node = root;
	        while(node != null){
	            if(node.val == target){
	                // ther cannot be any other node which can be closer than current
	                stack.push(node);
	                break;
	            }
	            else if(node.val > target){
	                stack.push(node);
	                node = node.left;
	            }
	            else{
	                node = node.right;
	            }
	        }
	        
	        return stack;
	    }
	    
	    // gets a stack of all the node from root which are closest smaller than target
	    private Stack loadPredessesor(TreeNode root, double target){
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        TreeNode node = root;
	        while(node != null){
	            if(node.val == target){
	                // ther cannot be any other node which can be closer than current
	                stack.push(node);
	                break;
	            }
	            else if(node.val > target){
	                node = node.left;
	            }
	            else{
	                stack.push(node);
	                node = node.right;
	            }
	        }
	        
	        return stack;
	    }
	    
	    //get the next closest greater entry and update stack
	    private int getNextSuccessor(Stack<TreeNode> stack){
	        TreeNode top = stack.pop();
	        int result = top.val;
	        top = top.right;
	        while(top!=null){
	            stack.push(top);
	            top = top.left;
	        }
	        
	        return result;
	    }
	    
	    //get the next closest smaller entry and update stack
	    private int getNextPredessesor(Stack<TreeNode> stack){
	        TreeNode top = stack.pop();
	        int result = top.val;
	        top = top.left;
	        while(top!=null){
	            stack.push(top);
	            top = top.right;
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
