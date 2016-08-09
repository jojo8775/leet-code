package interview.leetcode.prob;

/**
 * Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * @author jojo
 *
 */
public class MaximumDepthBinaryTree {
private int maxDepth = Integer.MIN_VALUE;
    
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }    
        
        dfs(root, 1);
        
        return maxDepth;
    }
    
    private void dfs(TreeNode node, int depth){
        if(node == null){
            return;
        }
        
        if(node.left == null && node.right == null){
            maxDepth = Math.max(depth, maxDepth);
            return;
        }
        
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
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
