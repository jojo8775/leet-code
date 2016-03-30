package interview.leetcode.tushar.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PostOrderTraversal
{
	public static void main(String[] args){
		TreeNode node = TreeUtil.createTree(15);
		TreeUtil.printTree(node);
		System.out.println();
		
		postOrderTraversal(node);
		System.out.println();
		postOrder(node);
	}
	
	private static void postOrderTraversal(TreeNode node){
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		nodeStack.add(node);
		
		Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
		
		while(!nodeStack.isEmpty()){
			TreeNode tempNode = nodeStack.peek();
			while(tempNode.leftChild != null && !visitedNodes.contains(tempNode.leftChild)){
				nodeStack.add(tempNode.leftChild);
				visitedNodes.add(tempNode.leftChild);
				tempNode = tempNode.leftChild;
			}
			
			tempNode = nodeStack.peek();
			if(tempNode.rightChild != null && !visitedNodes.contains(tempNode.rightChild)){
				nodeStack.add(tempNode.rightChild);
				visitedNodes.add(tempNode.rightChild);
			}
			else{
				tempNode = nodeStack.pop();
				System.out.print(tempNode.val + ", ");
			}
		}
	}
	
	private static void postOrder(TreeNode node){
		if(node == null){
			return;
		}
		
		postOrder(node.leftChild);
		postOrder(node.rightChild);
		System.out.print(node.val + ", ");
	}
}
