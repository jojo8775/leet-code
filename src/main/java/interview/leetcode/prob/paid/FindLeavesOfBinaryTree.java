package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].
 * @author jojo
 *
 */
public class FindLeavesOfBinaryTree {
    // idea is to find the height of the each node. That node will be a leaf in that height level
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leaves = new ArrayList<List<Integer>>();
        if(root == null){
            return leaves;
        }
        
        findHeight(root, leaves);
        return leaves;
    }
    
    private int findHeight(TreeNode node, List<List<Integer>> leaves){
        if(node == null){
            return 0;
        }
        
        int leftHeight = findHeight(node.left, leaves);
        int rightHeight = findHeight(node.right, leaves);
        
        int maxHeight = Math.max(leftHeight, rightHeight) + 1;
        
        if(leaves.size() < maxHeight){
            leaves.add(new ArrayList<Integer>());
        }
        
        leaves.get(maxHeight - 1).add(node.val);
        
        return maxHeight;
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
