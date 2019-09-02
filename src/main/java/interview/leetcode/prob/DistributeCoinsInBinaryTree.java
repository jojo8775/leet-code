package interview.leetcode.prob;

/**
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

 

Example 1:



Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
Example 2:



Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
Example 3:



Input: [1,0,2]
Output: 2
Example 4:



Input: [1,0,0,null,3]
Output: 4
 

Note:

1<= N <= 100
0 <= node.val <= N
Accepted
18,642
Submissions
27,596
 * @author jojo
 * Sep 2, 2019 2:13:16 AM
 */
public class DistributeCoinsInBinaryTree {
	public int distributeCoins(TreeNode root) {
        int[] moves = {0};
        
        postOrder(root, moves);
        
        return moves[0];
    }
    
    // [0] number of nodes in subtree, [1] number of coins in subtree
    private int[] postOrder(TreeNode node, int[] moves){
        if(node == null){
            return new int[]{0,0};
        }
        
        int[] left = postOrder(node.left, moves);
        int[] right = postOrder(node.right, moves);
        
        moves[0] += Math.abs(left[0] - left[1]) + Math.abs(right[0] - right[1]);
        
        return new int[]{left[0] + right[0] + 1, left[1] + right[1] + node.val};
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
