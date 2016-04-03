package interview.leetcode.tushar.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtil
{
	public static TreeNode createTree(int nodeCount){
		int count = 1;
		TreeNode node = new TreeNode(count);
		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		nodeQueue.add(node);

		while (count < nodeCount)
		{
			TreeNode tempNode = nodeQueue.poll();
			tempNode.leftChild = new TreeNode(++count);
			tempNode.rightChild = new TreeNode(++count);
			nodeQueue.add(tempNode.leftChild);
			nodeQueue.add(tempNode.rightChild);
		}
		
		return node;
	}
	
	public static TreeNode createTree(int[] val){
		TreeNode node = new TreeNode(val[0]);
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(node);
		
		for(int i=1; i<val.length; i=i+2){
			TreeNode tempNode = queue.poll();
			tempNode.leftChild = new TreeNode(val[i]);
			queue.add(tempNode.leftChild);
			
			if(i+1 < val.length){
				tempNode.rightChild = new TreeNode(val[i+1]);
				queue.add(tempNode.rightChild);
			}
		}
		
		return node;
	}
	
	public static void printTree(TreeNode node)
	{
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		nodes.add(node);

		while (!nodes.isEmpty())
		{
			List<TreeNode> tempNodes = new ArrayList<TreeNode>();
			for (TreeNode n : nodes)
			{
				System.out.print(n == null ? "null" : (n.val)  + ", ");

				if (n != null)
				{
					tempNodes.add(n.leftChild);
					tempNodes.add(n.rightChild);
				}
			}
			System.out.println();

			nodes = tempNodes;
		}
	}
}
