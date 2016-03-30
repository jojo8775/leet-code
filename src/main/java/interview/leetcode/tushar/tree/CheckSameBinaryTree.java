package interview.leetcode.tushar.tree;

public class CheckSameBinaryTree
{
	public static void main(String[] args)
	{
		TreeNode node1 = TreeUtil.createTree(5);
		TreeNode node2 = TreeUtil.createTree(5);
		System.out.println(areTreeSame(node1, node2));
	}

	private static boolean areTreeSame(TreeNode node1, TreeNode node2)
	{
		if (node1 == null && node2 == null)
		{
			return true;
		}

		if (node1.val != node2.val)
		{
			return false;
		}

		return areTreeSame(node1.leftChild, node1.leftChild) && areTreeSame(node1.rightChild, node1.rightChild);
	}
}
