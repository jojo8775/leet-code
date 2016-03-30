package interview.leetcode.tushar.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeTraversal
{
	public static void main(String[] args){
		TreeNode node = new TreeNode(1);
		
		Queue<TreeNode> nodeStack = new LinkedList<TreeNode>();
		nodeStack.add(node);
		
		int count = 1;
		while(count < 20){
			TreeNode tempNode = nodeStack.poll();
			tempNode.leftChild = new TreeNode(++count);
			tempNode.rightChild = new TreeNode(++count);
			nodeStack.add(tempNode.leftChild);
			nodeStack.add(tempNode.rightChild);
		}
		
		preOrder(node);
		System.out.println();
		inOrder(node);
		System.out.println();
		postOrder(node);
	}
	
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
