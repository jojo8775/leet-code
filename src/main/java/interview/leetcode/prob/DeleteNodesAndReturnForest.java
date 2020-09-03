package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 * @author jojo
 * Sep 2, 2020  11:08:42 PM
 */
public class DeleteNodesAndReturnForest {
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> nodesToDelete = new HashSet<>();
        for(int n : to_delete){
            nodesToDelete.add(n);
        }
        
        List<TreeNode> result = new ArrayList<>();
        
        TreeNode val = parse(root, nodesToDelete, result);
        if(val != null){
            result.add(0, val);
        }
        
        return result;
    }
    
    private TreeNode parse(TreeNode node, Set<Integer> nodesToDelete, List<TreeNode> result){
        if(node == null){
            return null;
        }
        
        node.left = parse(node.left, nodesToDelete, result);
        node.right = parse(node.right, nodesToDelete, result);
        
        if(nodesToDelete.contains(node.val)){
            if(node.left != null){
                result.add(node.left);
            }
            
            if(node.right != null){
                result.add(node.right);
            }
            
            return null;
        }
        
        return node;
    }
    
    private static class TreeNode{
    	TreeNode left;
    	TreeNode right;
    	int val;
    }
}
