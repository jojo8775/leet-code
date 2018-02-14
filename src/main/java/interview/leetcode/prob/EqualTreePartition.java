package interview.leetcode.prob;

/**
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000
 * @author jojo
 *Feb 13, 201812:42:51 AM
 */
public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root) {
        int total = findTotal(root);
        
        if(total%2 != 0){
            return false;
        }
        
        boolean[] result = {false};
        isSplitPossible(root, root, total, result);
        return result[0];
    }
    
    private int findTotal(TreeNode node){
        if(node == null){
            return 0;
        }
        
        int left = findTotal(node.left);
        int right = findTotal(node.right);
        
        return left + right + node.val;
    }
    
    private int isSplitPossible(TreeNode root, TreeNode node, int total, boolean[] result){
        if(node == null || result[0]){
            return 0; 
        }
        
        int left = isSplitPossible(root, node.left, total, result);
        int right = isSplitPossible(root, node.right, total, result);
        int curSum = node.val + left + right;
        if(!result[0] &&  (curSum * 2 == total && root != node)){
            result[0] = true;
        }
        
        return curSum;
    }
    
    private static class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
    }
}
