package interview.leetcode.prob;
/**
 * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 * @author jojo
 *
 */
public class FlattenBinaryTreeToLinkedList {
	/**
     * 1. root.left == null then root = root.right
     * 2. root.left != null then take left node
     *          a. keep traversing right till left.right != null
     *          b. left.rig = root.right
     *          c. root.right = root.left
     *          d. root.left = null
     * 3. keep doing 1 and 2 till root == null
     */ 
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){
                root = root.right;
            }
            else{
                TreeNode left = root.left;
                while(left.right != null){
                    left = left.right;
                }
                
                left.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }
    
    private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
