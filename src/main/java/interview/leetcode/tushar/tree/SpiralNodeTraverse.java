package interview.leetcode.tushar.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class SpiralNodeTraverse
{
	public static void main(String[] args){
		TreeNode node = TreeUtil.createTree(16);
		spiralTravers(node);
		System.out.println("=================");
		TreeUtil.printTree(node);
	}
	
	private static void spiralTravers(TreeNode node){
		
		Deque<TreeNode> deQueue = new ArrayDeque<TreeNode>();
		deQueue.add(node);
		
		boolean leftToRight = true;
		while(!deQueue.isEmpty()){
			Deque<TreeNode> tempDeQueue = new ArrayDeque<TreeNode>();
			while(!deQueue.isEmpty()){
				if(leftToRight){
					TreeNode tempNode = deQueue.pollFirst();
					System.out.print(tempNode.val + ", ");
					if(tempNode.leftChild != null){
						tempDeQueue.add(tempNode.leftChild);
					}
					if(tempNode.rightChild != null){
						tempDeQueue.add(tempNode.rightChild);
					}
				}
				else{
					TreeNode tempNode = deQueue.pollLast();
					System.out.print(tempNode.val + ", ");
					
					if(tempNode.rightChild != null){
						tempDeQueue.addFirst(tempNode.rightChild);
					}
					if(tempNode.leftChild != null){
						tempDeQueue.addFirst(tempNode.leftChild);
					}
				}
			}
			
			System.out.println();
			leftToRight = leftToRight ? false : true;
			deQueue = tempDeQueue;
		}
	}
}
