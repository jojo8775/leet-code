package interview.leetcode.tushar.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RootToLeafSum
{
	public static void main(String[] args)
	{
		TreeNode node = TreeUtil.createTree(12);
		TreeUtil.printTree(node);
		
		System.out.println(findPath(node, 15));
		System.out.println(findPath_rec(node, 15));
	}

	private static boolean findPath(TreeNode node, int sum)
	{

		Deque<TreeNode> nodeStack = new ArrayDeque<TreeNode>();
		nodeStack.add(node);
		sum -= node.val;

		Set<TreeNode> nodeSet = new HashSet();
		nodeSet.add(node);

		boolean pathFound = false;

		while (!nodeStack.isEmpty())
		{
			TreeNode tempNode = nodeStack.peek();
			if (sum < 0)
			{
				return false;
			}

			if (tempNode.leftChild == null && tempNode.rightChild == null && sum == 0)
			{
				pathFound = true;
				break;
			}

			if (tempNode.leftChild != null && !nodeSet.contains(tempNode.leftChild))
			{
				sum -= tempNode.leftChild.val;
				nodeStack.addFirst(tempNode.leftChild);
				nodeSet.add(tempNode.leftChild);
			}
			else if (tempNode.rightChild != null && !nodeSet.contains(tempNode.rightChild))
			{
				sum -= tempNode.rightChild.val;
				nodeStack.addFirst(tempNode.rightChild);
				nodeSet.add(tempNode.rightChild);
			}
			else
			{
				sum += tempNode.val;
				nodeStack.removeFirst();
			}
		}

		if (pathFound)
		{
			while (!nodeStack.isEmpty())
			{
				System.out.print(nodeStack.removeLast().val + ", ");
			}
		}

		return pathFound;
	}
	
	private static boolean findPath_rec(TreeNode node, int sum){
		if(sum < 0){
			return false;
		}
		
		if(node == null && sum == 0){
			return true;
		}
		
		if(findPath_rec(node.leftChild, sum - node.val)){
			System.out.print(node.val + ", ");
			return true;
		}
		
		if(findPath_rec(node.rightChild, sum - node.val)){
			System.out.print(node.val + ", ");
			return true;
		}
		
		return false;
	}
}
