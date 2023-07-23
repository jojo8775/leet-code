package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.

 

Example 1:

Flipped Trees Diagram
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Example 2:

Input: root1 = [], root2 = []
Output: true
Example 3:

Input: root1 = [], root2 = [1]
Output: false
 

Constraints:

The number of nodes in each tree is in the range [0, 100].
Each tree will have unique node values in the range [0, 99].
Accepted
132,444
Submissions
198,320
 * @author jojo
 * Jul 22, 2023 5:08:12 PM
 */
public class FlipEquivalentBinaryTrees {
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // return flipEquiv_rec(root1, root2);
        return flipEquiv_dfs(root1, root2);
    }
    
    private boolean flipEquiv_rec(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return true;
        }
        
        if(root1 == null || root2 == null || root1.val != root2.val){
            return false;
        }
        
        if(flipEquiv_rec(root1.left, root2.left) && flipEquiv_rec(root1.right, root2.right)){
            return true;
        }
        
        return flipEquiv_rec(root1.left, root2.right) && flipEquiv_rec(root1.right, root2.left);
    }
    
    // this uses canonical traversal technique. Which means the smallest child will be traversed first using DFS
    // each tree can have only one canonical representation. For two trees to be same, they must share the same 
    // canonical representation.
    private boolean flipEquiv_dfs(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        
        dfs(root1, l1);
        dfs(root2, l2);
        
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
    
    private void dfs(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        
        list.add(node.val);
        
        int leftVal = node.left != null ? node.left.val : -1;
        int rightVal = node.right != null ? node.right.val : -1;
        
        if(leftVal < rightVal){
            dfs(node.left, list);
            dfs(node.right, list);
        }
        else{
            dfs(node.right, list);
            dfs(node.left, list);
        }
        
        // this is to denote level breaker
        list.add(null);
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
