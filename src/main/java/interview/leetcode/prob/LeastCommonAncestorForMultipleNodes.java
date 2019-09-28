package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

public class LeastCommonAncestorForMultipleNodes {
	public TreeNode solution(TreeNode root, int[] vals) {
		List<List<TreeNode>> paths = new ArrayList<>();
		
		for(int val : vals) {
			List<TreeNode> path = new ArrayList<>();
			findPath(root, path, val);
			
			paths.add(path);
		}
		
		TreeNode result = root;
		boolean found = false;
		
		int depth = 1;
		while(!found) {
			TreeNode curNode = null;
			for(int i=1; i<paths.size(); i++) {
				List<TreeNode> path = paths.get(i);
				if(path.size() == depth) {
					found = true;
					break;
				}
				
				if(curNode != null & curNode != path.get(depth)) {
					found = true;
					break;
				}
				
				curNode = path.get(depth);
			}
			
			result = curNode;
			depth++;
		}
		
		return result;
	}
	
	private boolean findPath(TreeNode node, List<TreeNode> path, int target) {
		if(node == null) {
			return false;
		}
		
		path.add(node);
		if(node.val == target) {
			return true;
		}
		
		if(findPath(node.left, path, target)) {
			return true;
		}
		
		if(findPath(node.right, path, target)) {
			return true;
		}
		
		path.remove(path.size() - 1);
		return false;
	}
	
	private static class TreeNode{
		int val; 
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	
}
