package interview.leetcode.prob;

/**
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
Return the root of the reversed tree.

A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

The level of a node is the number of edges along the path between it and the root node.

 

Example 1:


Input: root = [2,3,5,8,13,21,34]
Output: [2,5,3,8,13,21,34]
Explanation: 
The tree has only one odd level.
The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
Example 2:


Input: root = [7,13,11]
Output: [7,11,13]
Explanation: 
The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
Example 3:

Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
Explanation: 
The odd levels have non-zero values.
The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
 

Constraints:

The number of nodes in the tree is in the range [1, 214].
0 <= Node.val <= 105
root is a perfect binary tree.
 * 
 * Dec 20, 2024 - 2:20:42 AM
 * Jojo 
 */
public class ReverseOddLevelsOfBinaryTree {
	public TreeNode reverseOddLevels(TreeNode root) {
        dfs(root.left, root.right, 1);

        return root;
    }

    private void dfs(TreeNode node1, TreeNode node2, int level){
        if(node1 == null || node2 == null){
            return;
        }

        if(level % 2 != 0){
            int temp = node1.val;

            node1.val = node2.val;
            node2.val = temp;
        }

        dfs(node1.left, node2.right, level + 1);
        dfs(node1.right, node2.left, level + 1);
    }
    

    /*
    public TreeNode reverseOddLevels(TreeNode root) {
        traverseDFS(root.left, root.right, 0);
        return root;
    }

    private void traverseDFS(
        TreeNode leftChild,
        TreeNode rightChild,
        int level
    ) {
        if (leftChild == null || rightChild == null) {
            return;
        }
        //If the current level is odd, swap the values of the children.
        if (level % 2 == 0) {
            int temp = leftChild.val;
            leftChild.val = rightChild.val;
            rightChild.val = temp;
        }

        traverseDFS(leftChild.left, rightChild.right, level + 1);
        traverseDFS(leftChild.right, rightChild.left, level + 1);
    }
    */
    
    private static class TreeNode{
    	TreeNode left;
    	TreeNode right;
    	int val;
    }
}
