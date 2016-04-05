package interview.leetcode.tushar.tree;

public class MaxBSTSubTree
{
	public static void main(String[] args){
		TreeNode root = createTree();
		MinMax result = postOrderTraversal(root);
		System.out.println("Is BST: " + result.isBST);
		System.out.println("Root: " + result.root.val);
		System.out.println("MaxHeight: " + result.maxBSTheight);
		System.out.println("Max size: " + result.maxBSTSize);
	}
	
	private static MinMax postOrderTraversal(TreeNode node){
		if(node == null){
			return null;
		}
		
		MinMax left = postOrderTraversal(node.leftChild);
		MinMax right = postOrderTraversal(node.rightChild);
		
		MinMax result = new MinMax();
		result.root = node;
		if(left == null && right == null){
			result.isBST = true;
			result.maxBSTSize = 1;
			
			return result;
		}
		
		if(left == null){
			if(node.rightChild.val > node.val){
				result.isBST = right.isBST;
				result.maxBSTSize = right.isBST ? 1 + right.maxBSTSize : right.maxBSTSize;
				result.maxBSTheight = right.isBST ? 1 + right.maxBSTheight : right.maxBSTheight;
				
			}
			else{
				result.maxBSTSize = right.maxBSTSize;
				result.maxBSTheight = right.maxBSTheight;
			}
			return result;
		}
		
		if(right == null){
			if(node.leftChild.val <= node.val){
				result.isBST = left.isBST;
				result.maxBSTSize = left.isBST ? 1 + left.maxBSTSize : left.maxBSTSize;
				result.maxBSTheight = left.isBST ? 1 + left.maxBSTheight : left.maxBSTheight;
			}
			else{
				result.maxBSTSize = left.maxBSTSize;
				result.maxBSTheight = left.maxBSTheight;
			}
			return result;
		}
		
		result.isBST = node.val < node.rightChild.val && node.val >= node.leftChild.val;
		result.isBST = left.isBST && right.isBST;
		
		if(result.isBST){
			result.maxBSTSize = 1 + left.maxBSTSize + right.maxBSTSize;
			result.maxBSTheight = 1 + Math.max(left.maxBSTheight, right.maxBSTheight);
			result.root = node;
		}
		else{
			result.maxBSTSize = Math.max(left.maxBSTSize, right.maxBSTSize);
			result.maxBSTheight = Math.max(left.maxBSTheight, right.maxBSTheight);
			if(left.maxBSTSize > right.maxBSTSize){
				result.root = left.root;
			}
			else{
				result.root = right.root;
			}
		}
		
		return result;
	}
	
	private static TreeNode createTree(){
		TreeNode node1 = new TreeNode(25);
		TreeNode root1 = node1;
		node1.leftChild = new TreeNode(18);
		node1 = node1.leftChild;
		TreeNode root2 = node1;
		node1.leftChild = new TreeNode(19);
		node1 = node1.leftChild;
		node1.rightChild = new TreeNode(15);
		node1 = node1.rightChild;
		
		node1 = root2;
		node1.rightChild = new TreeNode(20);
		node1 = node1.rightChild;
		
		node1.leftChild = new TreeNode(18);
		node1.rightChild = new TreeNode(25);
		
		node1 = root1;
		node1.rightChild = new TreeNode(50);
		node1 = node1.rightChild;
		
		node1.rightChild = new TreeNode(60);
		node1.leftChild = new TreeNode(35);
		root2 = node1.leftChild;
		
		node1 = node1.rightChild;
		node1.rightChild = new TreeNode(70);
		node1.leftChild = new TreeNode(55);
		
		node1 = root2;
		node1.rightChild = new TreeNode(40);
		node1.leftChild = new TreeNode(20);
		node1 = node1.leftChild;
		node1.rightChild = new TreeNode(25);
		
		TreeUtil.printTree(root1);
		
		return root1;
	}
	
	private static class MinMax{
		public  boolean isBST;
		public long maxBSTheight;
		public long maxBSTSize;
		public TreeNode root;
	}
}
