package interview.leetcode.string;

import java.util.Stack;

public class BSTIterator
{
	/**
	 * Implement an iterator over a binary search tree (BST). Your iterator will
	 * be initialized with the root node of a BST.
	 * 
	 * Calling next() will return the next smallest number in the BST.
	 * 
	 * Note: next() and hasNext() should run in average O(1) time and uses O(h)
	 * memory, where h is the height of the tree.
	 */
	Stack<TreeNode> nodeStack = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        populateStackWithLeftNode(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(nodeStack.isEmpty()){
            return false;
        }
        
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = nodeStack.pop();
        populateStackWithLeftNode(node.right);
        return node.val;
    }
    
    private void populateStackWithLeftNode(TreeNode node){
        while(node != null){
            nodeStack.push(node);
            node  = node.left;
        }
    }
    
    private static class TreeNode
    {
    	public int val;
    	public TreeNode left;
    	public TreeNode right;
    	
    	public TreeNode(int x){
    		this.val = val;
    	}
    }
}


