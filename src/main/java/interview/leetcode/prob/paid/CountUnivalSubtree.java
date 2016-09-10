package interview.leetcode.prob.paid;

/**
 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
 * @author jojo
 *
 */
public class CountUnivalSubtree {
    public int countUnivalSubtrees(TreeNode root) {
        countSubTree(root);
        return count;
    }
    
    private int count = 0;
    
    private boolean countSubTree(TreeNode node){
        if(node == null){
            return true;
        }
        
        boolean leftSubTree = countSubTree(node.left);
        boolean rightSubTree = countSubTree(node.right);
        
        boolean unival = true;
        
        if(node.left != null){
            unival = node.left.val == node.val;
        }
        
        if(node.right != null){
            unival = node.right.val == node.val && unival;
        }
        
        if(unival && leftSubTree && rightSubTree){
            count++;
        }
        
        return unival && leftSubTree && rightSubTree;
    }
    
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
