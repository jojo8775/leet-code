package interview.leetcode.prob;

import java.util.Stack;

public class DesignAnExpressionTreeWithEvaluation {
	/**
	 * This is the interface for the expression tree Node.
	 * You should not remove it, and you can define some classes to implement it.
	 */

	abstract class Node {
	    public abstract int evaluate();
	    // define your fields here
	};


	/**
	 * This is the TreeBuilder class.
	 * You can treat it as the driver code that takes the postinfix input 
	 * and returns the expression tree represnting it as a Node.
	 */

	class TreeBuilder {
	    Node buildTree(String[] postfix) {
	        return new TreeNode(postfix);
	    }
	};

	class TreeNode extends Node{
	    private String[] postfix;
	    
	    public TreeNode(String[] postfix){
	        this.postfix = postfix;
	    }
	    
	    public  int evaluate(){
	        Stack<Integer> stack = new Stack<>();
	        
	        for(int i=0; i<postfix.length; i++){
	            String s = postfix[i];
	            
	            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
	                int right = stack.pop(), left = stack.pop();
	                
	                switch(s) {
	                	case "+" : return left + right;
	                	case "-" : return left - right;
	                	case "*" : return left * right;
	                	case "/" : return left / right;
	                }
	                
	                if(s.equals("+")){
	                    stack.push(left + right);
	                }
	                else if(s.equals("-")){
	                    stack.push(left - right);
	                }
	                else if(s.equals("*")){
	                    stack.push(left * right);
	                }
	                else{ 
	                    stack.push(left / right);
	                }
	            }
	            else{
	                stack.push(Integer.valueOf(s));
	            }
	        }
	        
	        return stack.pop();
	    }
	}


	/**
	 * Your TreeBuilder object will be instantiated and called as such:
	 * TreeBuilder obj = new TreeBuilder();
	 * Node expTree = obj.buildTree(postfix);
	 * int ans = expTree.evaluate();
	 */
}
