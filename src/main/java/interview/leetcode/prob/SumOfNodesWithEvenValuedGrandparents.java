package interview.leetcode.prob;

/**
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.

 

Example 1:



Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 

Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
Accepted
28,322
Submissions
34,023
 * @author jojo
 * Jun 18, 2020  11:26:38 PM
 */
public class SumOfNodesWithEvenValuedGrandparents {
	public int sumEvenGrandparent(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        int sum = 0;
        if(root.val % 2 == 0){
            if(root.left != null && root.left.left != null){
                sum = root.left.left.val;
            }
            
            if(root.left != null && root.left.right != null){
                sum += root.left.right.val;
            }
            
            if(root.right != null && root.right.left != null){
                sum += root.right.left.val;
            }
            
            if(root.right != null && root.right.right != null){
                sum += root.right.right.val;
            }
        }
        
        return sum + sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
    }
	
	private static class TreeNode{
		TreeNode left, right;
		int val;
	}
}
