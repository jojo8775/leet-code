package interview.leetcode.prob;

/**
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.

 

Example 1:


Input: root = [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: root = [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
Node.val == 0
Accepted
75,463
Submissions
173,861
 * @author jojo
 * Jun 16, 2022 11:30:01 PM
 */
public class BinaryTreeCameras {
	 public int minCameraCover(TreeNode root) {
	        int[] result = solve(root);
	        
	        return Math.min(result[1], result[2]);
	    }
	    
	    private int[] solve(TreeNode node){
	        if(node == null){
	            return new int[]{0,0,99999};
	        }
	        
	        int[] left = solve(node.left);
	        int[] right = solve(node.right);
	        
	        int leftMin = Math.min(left[1], left[2]);
	        int rightMin = Math.min(right[1], right[2]);
	        
	        // represents state when current node is not covered but lower nodes are covered without camera 
	        int s0 = left[1] + right[1];
	        
	        // represents state when current node is covered and of the lower nodes have camera 
	        int s1 = Math.min(left[2] + rightMin, right[2] + leftMin);
	        
	        // represents state when current node has camera and all the surrounding nodes are covered
	        int s2 = 1 + Math.min(left[0], leftMin) + Math.min(right[0], rightMin);
	        
	        return new int[]{s0, s1, s2};
	    }
	    
	    private static class TreeNode{
	    	TreeNode left, right;
	    }
}
