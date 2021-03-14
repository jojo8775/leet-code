package interview.leetcode.prob;

/**
 * 
Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

 

Example 1:


Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
Example 2:

Input: preorder = [1,3]
Output: [1,null,3]
 

Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 108
All the values of preorder are unique.
Accepted
155,605
Submissions
197,482
 * @author jojo
 * Mar 6, 2021  7:01:41 PM
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
	public TreeNode bstFromPreorder(int[] preorder) {
//      Stack<TreeNode> stack = new Stack<>();
//      TreeNode root = new TreeNode(preorder[0]); 
//      stack.push(root);

//      for(int i=1; i<preorder.length; i++){
//          TreeNode node = new TreeNode(preorder[i]);

//          TreeNode parent = null;
//          while(!stack.isEmpty()){
//              if(stack.peek().val > node.val){
//                  if(parent == null){
//                      stack.peek().left = node;
//                  }
//                  else{
//                      parent.right = node;
//                  }

//                  break;
//              }
//              else{
//                  parent = stack.pop();
//              }
//          }

//          if(stack.isEmpty()){
//              parent.right = node;
//          }

//          stack.push(node);
//      }

//      return root;

		return construct(preorder, 0, preorder.length - 1);
	}

	private TreeNode construct(int[] preOrder, int beg, int end) {
		if (beg > end) {
			return null;
		}

		TreeNode node = new TreeNode(preOrder[beg]);

		int i = beg + 1;
		while (i <= end) {
			if (preOrder[i] > preOrder[beg]) {
				break;
			}
			i++;
		}

		node.left = construct(preOrder, beg + 1, i - 1);
		node.right = construct(preOrder, i, end);

		return node;

	}

	private static class TreeNode {
		TreeNode left, right;
		int val;
		
		public TreeNode(int val) {
			
		}
	}
}
