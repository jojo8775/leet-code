package interview.leetcode.prob;

/**
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.

 

Example 1:


Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
Example 2:

Input: preorder = [1], postorder = [1]
Output: [1]
 

Constraints:

1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
Accepted
63,519
Submissions
91,708
 * @author jojo
 * Dec 30, 2021 8:30:11 AM
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost(pre, 0, pre.length - 1, post, 0, pre.length - 1);
    }
    
    private TreeNode constructFromPrePost(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        // Base cases.
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }
        
        // Build root.
        TreeNode root = new TreeNode(pre[preStart]);
        
        // Locate left subtree.
        int leftSubRootInPre = preStart + 1; 
        int leftSubRootInPost = findLeftSubRootInPost(pre[leftSubRootInPre], post, postStart, postEnd);
        
//      e.g.   Given preorder : 1 2 4 5 3 6;     postorder: 4 5 2 6 3 1.
//      We see it as preorder : 1 (2 4 5) (3 6); postorder: (4 5 2) (6 3) 1 [to be explained afterwards]
// 		That can be decreased to subproblems A, B, C: 
//        		A. preorder : 1; postorder: 1 =>
//        		 1
//        		B. preorder : (2 4 5); postorder: (4 5 2) => 
//        		   2
//        		  / \
//        		 4   5
//        		C. preorder : (3 6); postorder: (6 3) => 
//        		   3
//        		  / 
//        		 6     or
//        		   3
//        		    \
//        		     6
//        		* Then we conquer the subproblems => A.left = B; A.right = C;
//        		   1
//        		  / \
//        		 2   3
//        		/ \  /
//        		4  5 6
        int leftSubEndInPre = leftSubRootInPre + (leftSubRootInPost - postStart);
        
        // Divide.
        TreeNode leftSubRoot = constructFromPrePost(pre, leftSubRootInPre, leftSubEndInPre, post, postStart, leftSubRootInPost);  
        TreeNode rightSubRoot = constructFromPrePost(pre, leftSubEndInPre + 1, preEnd, post, leftSubRootInPost + 1, postEnd - 1);
        
        // Conquer.      
        root.left = leftSubRoot;
        root.right = rightSubRoot;
        
        return root;
    }
    
    private int findLeftSubRootInPost(int leftSubRootVal, int[] post, int postStart, int postEnd) {
        for (int i = postStart; i <= postEnd; i++) {
            if (post[i] == leftSubRootVal) {
                return i;
            }
        }
        
        throw new IllegalArgumentException();
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left = null, right = null;
    	
    	public TreeNode(int val) {
    		this.val = val;
    	}
    }
}
