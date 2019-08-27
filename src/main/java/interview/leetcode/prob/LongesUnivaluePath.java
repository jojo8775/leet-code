package interview.leetcode.prob;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

 

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2

 

Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2

 

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 * @author jojo
 * Aug 26, 2019 10:20:04 PM
 */
public class LongesUnivaluePath {
	public int longestUnivaluePath(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        int[] len = {0};
        findLen(root, root.val, len);
        
        return len[0] - 1; // the answer is expected to be zero based.
    }
    
    private int findLen(TreeNode node, int val, int[] len){
        if(node == null){
            return 0;
        }
        
        int left = findLen(node.left, node.val, len);
        int right = findLen(node.right, node.val, len);
        
        len[0] = Math.max(len[0], left + right + 1);
        
        if(val == node.val){
            return 1 + Math.max(left, right);
        }
        
        return 0;
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
