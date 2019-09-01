package interview.leetcode.prob;

/**
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

 

Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 

Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.
Accepted
13,323
Submissions
21,755
 * @author jojo
 *Sep 1, 201910:37:40 AM
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    public int maxAncestorDiff(TreeNode root) {
        return findDiff(root, root.val, root.val);
    }
    
    private int findDiff(TreeNode node, int min, int max){
        if(node == null){
            return max - min;
        }
        
        int nodeMin = Math.min(node.val, min);
        int nodeMax = Math.max(node.val, max);
    
        // finding the max of right and min of left from each node. 
        return Math.max(findDiff(node.left, nodeMin, nodeMax), findDiff(node.right, nodeMin, nodeMax));
    }
    
    private static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
