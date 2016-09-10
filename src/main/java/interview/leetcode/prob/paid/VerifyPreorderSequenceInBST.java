package interview.leetcode.prob.paid;

import java.util.Stack;

public class VerifyPreorderSequenceInBST {
    // the idea is to parse the input and in a BST left node is 
    // smaller than root and right is larger than root
    public boolean verifyPreorder(int[] preorder) {
	    int lowestAllowed = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0; i<preorder.length; i++){
			if(preorder[i] < lowestAllowed){
				return false;
			}
			
			// this will take the parent node of current node as reference to lowest allowed
			while(!stack.isEmpty() && stack.peek() < preorder[i]){
				lowestAllowed = stack.pop();
			}
			
			stack.push(preorder[i]);
		}
		
		return true;
	}
}
