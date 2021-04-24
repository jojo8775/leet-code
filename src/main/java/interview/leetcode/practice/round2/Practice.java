package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.List;

public class Practice {
	public List<String> findPath(TreeNode node){
		List<String> result = new ArrayList<>();
		findPath(node, new ArrayList<Integer>(), result);
		
		return result;
	}
	
	private void findPath(TreeNode node, List<Integer> pathSofar, List<String> result) {
		if(node == null) {
			return;
		}
		
		if(node.left == null && node.right == null) {
			StringBuilder sb = new StringBuilder();
			pathSofar.forEach(x -> sb.append(x).append("->"));
			
			sb.append(node.val);
			result.add(sb.toString());
			
			return;
		}
		
		pathSofar.add(node.val);
		findPath(node.left, pathSofar, result);
		findPath(node.right, pathSofar, result);
		pathSofar.remove(pathSofar.size() - 1);
	}
	
	
	private static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
	}
}
