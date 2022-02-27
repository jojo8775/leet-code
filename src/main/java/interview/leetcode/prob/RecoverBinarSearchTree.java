package interview.leetcode.prob;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you
 * devise a constant space solution?
 * 
 * @author jojo
 *
 */
public class RecoverBinarSearchTree {
    TreeNode t1 = null, t2 = null, prev = null;
    
    public void recoverTree(TreeNode root) {
        findRoot(root);
        
        int temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;
    }
    
    private void findRoot(TreeNode node){
        if(node == null){
            return;
        }
        
        findRoot(node.left);
        
        if(prev != null){
            if(t1 == null && prev.val >= node.val){
                t1 = prev;
                // System.out.println("t1:"  + t1.val);
            }

            if(t1 != null && prev.val >= node.val){
                t2 = node;
                // System.out.println("t2:"  + t2.val);
            }
        }
        
        prev = node;
        
        findRoot(node.right);
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
