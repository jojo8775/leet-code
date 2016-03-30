package interview.leetcode.tushar.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class InOrderTraversal
{
	public static void main(String[] args){
		TreeNode node = TreeUtil.createTree(15);
		inOrderTraversal(node);
		System.out.println();
		inOrder(node);
	}
	
	private static void inOrderTraversal(TreeNode node){
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		nodeStack.add(node);
		
		Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
		
		while(!nodeStack.isEmpty()){
			TreeNode tempNode = nodeStack.peek();
			while(tempNode.leftChild != null && !visitedNodes.contains(tempNode.leftChild)){
				nodeStack.push(tempNode.leftChild);
				visitedNodes.add(tempNode.leftChild);
				tempNode = tempNode.leftChild;
			}
			
			tempNode = nodeStack.pop();
			System.out.print(tempNode.val + ", ");
			if(tempNode.rightChild != null){
				nodeStack.add(tempNode.rightChild);
			}
		}
	}
	
	private static void inOrder(TreeNode node){
		if(node == null){
			return;
		}
		
		inOrder(node.leftChild);
		System.out.print(node.val + ", ");
		inOrder(node.rightChild);
	}
}
