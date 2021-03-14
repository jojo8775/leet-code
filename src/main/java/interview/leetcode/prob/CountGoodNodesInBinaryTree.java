package interview.leetcode.prob;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

 

Example 1:



Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
Example 2:



Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.
 

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
Accepted
33,022
Submissions
46,588
 * @author jojo
 * Mar 10, 2021  10:59:00 PM
 */
public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        int[] result = new int[]{0};
        findNodes(root, Integer.MIN_VALUE, result);
        
        return result[0];
    }
    
    private void findNodes(TreeNode node, int maxSofar, int[] result){
        if(node == null){
            return;
        }
        
        if(node.val >= maxSofar){
            result[0]++;
        }
        
        maxSofar = Math.max(maxSofar, node.val);
        findNodes(node.left, maxSofar, result);
        findNodes(node.right, maxSofar, result);
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
