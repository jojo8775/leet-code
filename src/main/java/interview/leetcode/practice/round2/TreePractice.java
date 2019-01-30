package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePractice {
	public void blaa() {
		System.out.println("some thing");
	}
	
	public List<Integer> parseLeafNode(TreeNode node){
		List<Integer> result = new ArrayList<>();
		parse(node, result);
		return result;
	}
	
	private void parse(TreeNode node, List<Integer> result) {
		if(node == null) {
			return;
		}
		
		if(node.left == null && node.right == null) {
			result.add(node.val);
		}
		
		parse(node.left, result);
		parse(node.right, result);
	}
	
	private static class TreeNode {
		int val;
		TreeNode left, right;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		TreeNode node = createTree(arr, 0, 9);
		List<Integer> result = new TreePractice().parseLeafNode(node);
		System.out.println("Parse leaf : ");
		result.stream().forEach(entry -> System.out.print(entry + ", "));
		
		System.out.println("\nParse in-order");
		result = new TreePractice().inorderTraversal(node);
		result.stream().forEach(entry -> System.out.print(entry + ", "));
		
		System.out.println("\nParse pre-order");
		result = new TreePractice().preOrderTraversal(node);
		result.stream().forEach(entry -> System.out.print(entry + ", "));
	}
	
	public List<Integer> inorderTraversal(TreeNode node){
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		
		while(node != null || !stack.isEmpty()) {
			if(node != null) {
				stack.push(node);
				node = node.left;
			}
			else {
				node = stack.pop();
				result.add(node.val);
				node = node.right;
			}
		}
		
		return result;
	}
	
	public List<Integer> preOrderTraversal(TreeNode node){
		List<Integer> result = new ArrayList<>();
		
		Stack<TreeNode> stack = new Stack<>();
		while(node != null || !stack.isEmpty()) {
			if(node != null) {
				result.add(node.val);
				stack.push(node);
				node = node.left;
			}
			else {
				node = stack.pop();
				node = node.right;
			}
		}
		
		return result;
	}
	
	private static TreeNode createTree(int[] arr, int left, int right) {
		if(left > right) {
			return null;
		}
		
		int mid = (right - left)/2 + left;
		TreeNode node = new TreeNode();
		node.val = arr[mid];
		node.left = createTree(arr, left, mid - 1);
		node.right = createTree(arr, mid + 1, right);
		
		return node;
	}
}
