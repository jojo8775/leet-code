package interview.leetcode.prob;

/**
 * Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 * @author jojo
 *
 */
public class BinaryTreeMaxSumPath {
	//Stores the max value at any node
	private int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        dfs(root);
        return maxValue;
    }
    
    private int dfs(TreeNode node) {
        if (node == null){
        	return 0;
        }
        
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        
        //updating max if left and right child are contributing
        maxValue = Math.max(maxValue, left + right + node.val);
        
        // returning the max path or left or right with form part other node child
        return Math.max(left, right) + node.val;
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
