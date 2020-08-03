package interview.leetcode.practice;

public class BSTAndDistance {
	public int findMinimumDistanceBetweenNodes(TreeNode node, TreeNode n1, TreeNode n2) {
		TreeNode lca = findAncestor(node, n1, n2);
		return distance(lca, n1) + distance(lca, n2);
	}
	
	private TreeNode findAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
		if(root == null || root == n1 || root == n2) {
			return root;
		}
		
		if(n1.val > root.val && n2.val > root.val) {
			return findAncestor(root.right, n1, n2);
		}
		else if(n1.val < root.val && n2.val < root.val) {
			return findAncestor(root.left, n1, n2);
		}
		else {
			return root; 
		}
	}
	
	private int distance(TreeNode node, TreeNode a) {
		if(node == a) {
			return 0;
		}
		
		return 1 + node.val > a.val ? distance(node.left, a) : distance(node.right, a);
	}
	
	private static class TreeNode{
		int val;
		TreeNode left, right;
	}
}
