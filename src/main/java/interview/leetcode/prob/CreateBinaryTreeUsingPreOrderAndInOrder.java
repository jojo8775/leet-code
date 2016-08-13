package interview.leetcode.prob;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * @author jojo
 *
 */
public class CreateBinaryTreeUsingPreOrderAndInOrder {
	private int preOrderIdx = 0;

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return createTree(preorder, inorder, 0, inorder.length - 1);
	}

	/**
	 *1. take index in pre-order
	 *2. find it in in-order
	 *3. create a node 
	 *4. call recursively for left child beg ~ in-order index -1 
	 *5. call recursively for right child in-order index +1 ~ end  
	 */
	private TreeNode createTree(int[] preOrder, int[] inOrder, int beg, int end) {
		if (beg > end) {
			return null;
		}

		TreeNode node = new TreeNode(preOrder[preOrderIdx++]);

		int inOrderIdx = -1;
		for (int i = beg; i <= end; i++) {
			if (node.val == inOrder[i]) {
				inOrderIdx = i;
				break;
			}
		}

		node.left = createTree(preOrder, inOrder, beg, inOrderIdx - 1);
		node.right = createTree(preOrder, inOrder, inOrderIdx + 1, end);

		return node;
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
