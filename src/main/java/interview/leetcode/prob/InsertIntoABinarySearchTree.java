package interview.leetcode.prob;

/**
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
Accepted
59,010
Submissions
76,680
 * @author jojo
 * Sep 13, 2019 2:58:00 AM
 */
public class InsertIntoABinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        insert(root, val);
        return root;
    }
    
    private void insert(TreeNode node, int val){
        if(val > node.val){
            if(node.right != null){
                 insert(node.right, val);
            }
            else{
                node.right = new TreeNode(val);
            }
        }
        else{
            if(node.left != null){
                insert(node.left, val);
            }
            else{
                node.left = new TreeNode(val);
            }
        }
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    	
    	public TreeNode(int val) {
    		this.val = val;
    	}
    }
}
