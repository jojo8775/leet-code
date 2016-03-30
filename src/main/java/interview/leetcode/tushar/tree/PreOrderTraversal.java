package interview.leetcode.tushar.tree;

import java.util.Stack;

public class PreOrderTraversal
{
	public static void main(String[] args){
		TreeNode node = TreeUtil.createTree(15);
		preOrderTraversal(node);
		System.out.println();
		preOrder(node);
	}
	
	private static void preOrderTraversal(TreeNode node){
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		nodeStack.add(node);
		
		while(!nodeStack.isEmpty()){
			TreeNode tempNode = nodeStack.pop();
			System.out.print(tempNode.val + ", ");
			
			if(tempNode.rightChild != null){
				nodeStack.push(tempNode.rightChild);
			}
			
			if(tempNode.leftChild != null){
				nodeStack.push(tempNode.leftChild);
			}
		}
	}
	
	private static void preOrder(TreeNode node){
		if(node == null){
			return;
		}
		
		System.out.print(node.val + ", ");
		preOrder(node.leftChild);
		preOrder(node.rightChild);
	}
}
