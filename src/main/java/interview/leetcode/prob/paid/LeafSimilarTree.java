package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

 

Example 1:


Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true
Example 2:

Input: root1 = [1], root2 = [1]
Output: true
Example 3:

Input: root1 = [1], root2 = [2]
Output: false
Example 4:

Input: root1 = [1,2], root2 = [2,2]
Output: true
Example 5:


Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false
 

Constraints:

The number of nodes in each tree will be in the range [1, 200].
Both of the given trees will have values in the range [0, 200].
Accepted
122,759
Submissions
190,379
 * @author jojo
 * Apr 18, 2021  9:02:04 AM
 */
public class LeafSimilarTree {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        populateLeaf(root1, l1);
        
        List<Integer> l2 = new ArrayList<>();
        populateLeaf(root2, l2);
        
        if(l1.size() != l2.size()){
            return false;
        }
        
        for(int i=0; i<l1.size(); i++){
            if(l1.get(i) != l2.get(i)){
                return false;
            }
        }
        
        return true;
    }
    
    private void populateLeaf(TreeNode node, List<Integer> l1){
        if(node == null){
            return;
        }
        
        if(node.left == null && node.right == null){
            l1.add(node.val);
            return;
        }
        
        populateLeaf(node.left, l1);
        populateLeaf(node.right, l1);
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
