package interview.leetcode.prob;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * @author jojo
 *
 */
public class CreateBinaryTreeUsingPostOrderAndInOrder {
	private int postOrderIdx;

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		postOrderIdx = postorder.length - 1;
		return createTree(inorder, postorder, 0, postorder.length - 1);
	}

	private TreeNode createTree(int[] inOrder, int[] postOrder, int beg, int end) {
		if (beg > end) {
			return null;
		}

		TreeNode node = new TreeNode(postOrder[postOrderIdx--]);

		int inOrderIdx = -1;
		for (int i = beg; i <= end; i++) {
			if (node.val == inOrder[i]) {
				inOrderIdx = i;
				break;
			}
		}

		node.right = createTree(inOrder, postOrder, inOrderIdx + 1, end);
		node.left = createTree(inOrder, postOrder, beg, inOrderIdx - 1);

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
