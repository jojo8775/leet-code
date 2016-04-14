package interview.leetcode.concepts;

import java.util.ArrayList;
import java.util.List;

public class AVLTree
{
	public static void main(String[] args)
	{
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		TreeNode root = createAVLTree(nums);
		printTree(root);
	}

	private static TreeNode createAVLTree(int[] arr)
	{
		TreeNode root = new TreeNode(arr[0]);

		for (int i = 1; i < arr.length; i++)
		{
			TreeNode currentNode = root;
			printTree(currentNode);
			System.out.println("==============================");
			root = createAVLTree(currentNode, arr[i]);
		}

		return root;
	}

	private static TreeNode createAVLTree(TreeNode node, int val)
	{
		if (node.value < val)
		{
			if (node.rightNode == null)
			{
				node.rightNode = new TreeNode(val);
				node.height = 1;
				return node;
			}

			node.rightNode = createAVLTree(node.rightNode, val);
		}
		else
		{
			if (node.leftNode == null)
			{
				node.leftNode = new TreeNode(val);
				node.height = 1;
				return node;
			}

			node.leftNode = createAVLTree(node.leftNode, val);
		}

		int leftHeight = (node.leftNode == null) ? 0 : node.leftNode.height + 1;
		int rightHeight = (node.rightNode == null) ? 0 : node.rightNode.height + 1;

		node.height = Math.max(leftHeight, rightHeight);

		if (Math.abs(leftHeight - rightHeight) > 1)
		{
			if (leftHeight > rightHeight)
			{
				int subTreeLeftHeight = node.leftNode.leftNode == null ? 0 : node.leftNode.leftNode.height;
				int subTreeRightHeight = node.leftNode.rightNode == null ? 0 : node.leftNode.rightNode.height;

				if (subTreeLeftHeight < subTreeRightHeight)
				{
					node.leftNode = rightRotate(node.leftNode);
				}
				
				node = leftRotate(node);
			}
			else{
				int subTreeLeftHeight = node.rightNode.leftNode == null ? 0 : node.rightNode.leftNode.height;
				int subTreeRightHeight = node.rightNode.rightNode == null ? 0 : node.rightNode.rightNode.height;

				if (subTreeLeftHeight > subTreeRightHeight)
				{
					node.rightNode = leftRotate(node.rightNode);
				}
				
				node = rightRotate(node);
			}
		}
		
		return node;
	}

	private static TreeNode leftRotate(TreeNode oldRoot)
	{
		TreeNode newRoot = oldRoot.leftNode;
		oldRoot.leftNode = oldRoot.leftNode.rightNode;
		newRoot.rightNode = oldRoot;
		oldRoot.height = setHeight(oldRoot);
		newRoot.height = setHeight(newRoot);
		return newRoot;
	}

	private static TreeNode rightRotate(TreeNode oldRoot)
	{
		TreeNode newRoot = oldRoot.rightNode;
		oldRoot.rightNode = oldRoot.rightNode.leftNode;
		newRoot.leftNode = oldRoot;
		oldRoot.height = setHeight(oldRoot);
		newRoot.height = setHeight(newRoot);
		return newRoot;
	}

	private static int setHeight(TreeNode node)
	{
		if (node == null)
		{
			return 0;
		}

		node.height = 1 + Math.max(setHeight(node.leftNode), setHeight(node.rightNode));
		return node.height;
	}

	private static void printTree(TreeNode node)
	{
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		nodes.add(node);

		while (!nodes.isEmpty())
		{
			List<TreeNode> tempNodes = new ArrayList<TreeNode>();
			for (TreeNode n : nodes)
			{
				System.out.print((n == null ? "null" : (n.value + "(" + n.height + ")")) + ", ");

				if (n != null)
				{
					tempNodes.add(n.leftNode);
					tempNodes.add(n.rightNode);
				}
			}
			System.out.println();

			nodes = tempNodes;
		}
	}

	private static class TreeNode
	{
		public int value;
		public TreeNode leftNode;
		public TreeNode rightNode;
		public int height;

		public TreeNode(int value)
		{
			this.value = value;
		}
	}
}
