package interview.leetcode.prob;

/**
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.

Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).

 

Example 1:



Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
Example 2:



Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]
Example 3:



Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with value (target = 2) are removed at each step.
Example 4:

Input: root = [1,1,1], target = 1
Output: []
Example 5:

Input: root = [1,2,3], target = 1
Output: [1,2,3]
 

Constraints:

1 <= target <= 1000
The given binary tree will have between 1 and 3000 nodes.
Each node's value is between [1, 1000].
Accepted
19,030
Submissions
26,219
 * @author jojo
 * Jun 12, 2020  8:46:35 AM
 */
public class DeleteLeavesWithAGivenValue {
	public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null){
            return null;
        }
        
        TreeNode left = removeLeafNodes(root.left, target);
        TreeNode right = removeLeafNodes(root.right, target);
        
        root.left = left;
        root.right = right;
        
        if(left == null && right == null && root.val == target){
            return null;
        }
        else{
            return root;
        }
    }
	
	public TreeNode removeLeafNodes_adv(TreeNode root, int target) {
        if(root == null){
            return null;
        }
        
        root.left = removeLeafNodes_adv(root.left, target);
        root.right = removeLeafNodes_adv(root.right, target);
        
        if(root.val == target && root.left == null && root.right == null){
            return null;
        }
        
        return root;
    }
	
	private static class TreeNode{
		TreeNode left, right;
		int val;
	}
}
