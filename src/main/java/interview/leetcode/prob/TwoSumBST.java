package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

 

Example 1:



Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.
Example 2:



Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false
 

Constraints:

Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9

 * @author jojo
 * Nov 7, 2019 12:22:05 AM
 */
public class TwoSumBST {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        inOrder(root1, left);
        inOrder(root2, right);
        
        int i = 0, j = right.size() - 1, leftLen = left.size();
        while(i < leftLen && j >= 0){
            int val = left.get(i) + right.get(j);
            if(val == target){
                return true;
            }
            else if(val < target){
                i++;
            }
            else{
                j--;
            }
        }
        
        return false;
    }
    
    private void inOrder(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }
    
    private static class TreeNode{
    	int val; 
    	TreeNode left, right;
    }
}
