package interview.leetcode.tushar.tree;

public class CheckIfBinarySearchTree
{
	public static void main(String[] args){
		int[] val = {8,6,10,3,7,9,13};
		TreeNode node = TreeUtil.createTree(val);
		TreeUtil.printTree(node);
		System.out.println(isBinarySearchTree(node));
	}
	
	private static boolean isBinarySearchTree(TreeNode node){
		
		if(node == null || (node.leftChild == null && node.rightChild == null)){
			return true;
		}
		else if(node.leftChild == null && node.rightChild.val >= node.val){
			return isBinarySearchTree(node.rightChild);
		}
		else if(node.rightChild == null && node.leftChild.val < node.val){
			return isBinarySearchTree(node.leftChild);
		}
		else if(node.rightChild.val < node.val || node.leftChild.val >= node.val){
			return false;
		}
		
		return isBinarySearchTree(node.leftChild) && isBinarySearchTree(node.rightChild);
	}
}
