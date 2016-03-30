package interview.leetcode.tushar.tree;

public class BinaryTreeTraversal
{
	
	private static void postOrder(TreeNode node){
		if(node == null){
			return;
		}
		
		postOrder(node.leftChild);
		postOrder(node.rightChild);
		System.out.print(node.val + ", ");
	}
	
	//VLR
	private static void preOrder(TreeNode node){
		if(node == null){
			return;
		}
		
		System.out.print(node.val + ", ");
		preOrder(node.leftChild);
		preOrder(node.rightChild);
	}
	
	//LVR
	private static void inOrder(TreeNode node){
		
		if(node == null){
			return;
		}
		
		inOrder(node.leftChild);
		System.out.print(node.val + ", ");
		inOrder(node.rightChild);
	}
}
