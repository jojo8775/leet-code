package interview.leetcode.prob;

/**
 * Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:



Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
Output: 20
Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
Example 2:



Input: root = [4,3,null,1,2]
Output: 2
Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
Example 3:

Input: root = [-4,-2,-5]
Output: 0
Explanation: All values are negatives. Return an empty BST.
Example 4:

Input: root = [2,1,3]
Output: 6
Example 5:

Input: root = [5,4,8,3,null,6,3]
Output: 7
 

Constraints:

The given binary tree will have between 1 and 40000 nodes.
Each node's value is between [-4 * 10^4 , 4 * 10^4].
 * @author jojo
 * Jun 10, 2020  11:58:55 PM
 */
public class MaximumSumBSTInABinaryTree {
	public int maxSumBST(TreeNode root) {
        int[] max = new int[]{0};
        find(root, max);
        return max[0];
    }
    
    private int[] find(TreeNode node, int[] max){
        if(node == null){
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 0};
        }
        
        int[] left = find(node.left, max);
        int[] right = find(node.right, max);
        
        int[] res;
        
        if(left[2] == right[2] && left[2] == 1 && node.val > left[1] && node.val < right[0]){
            // min, max, bst flag, sum
            res = new int[]{Math.min(left[0], node.val), Math.max(right[1], node.val), 1, left[3] + right[3] + node.val};
            
            // max sum so far.
            max[0] = Math.max(max[0], res[3]);
        }
        else{
            // sub trees are no longer a bst so no need to compute. 
            res = new int[4];
        }
        
        
        return res;
    }
    
    private static class TreeNode{
    	TreeNode left, right;
    	int val;
    }
}
